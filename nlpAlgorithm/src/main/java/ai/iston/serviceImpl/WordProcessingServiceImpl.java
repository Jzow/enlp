package ai.iston.serviceImpl;

import ai.iston.dao.mapper.VocabLevelMapper;
import ai.iston.dao.service.VocabClassificationDaoService;
import ai.iston.dao.service.VocabLogicConnectionDaoService;
import ai.iston.model.bo.ParseWordBo;
import ai.iston.model.bo.VocabLevelBo;
import ai.iston.model.bo.WordBo;
import ai.iston.model.entity.Article;
import ai.iston.model.entity.VocabClassification;
import ai.iston.model.entity.VocabLogicConnection;
import ai.iston.model.modelmapper.WordModelMapper;
import ai.iston.model.vo.ParseWordVo;
import ai.iston.model.vo.SentenceWordVo;
import ai.iston.model.vo.WordInfoVo;
import ai.iston.service.WordProcessingService;
import ai.iston.utils.PropertiesFactory;
import ai.iston.utils.constants.AnnotatorConstants;
import ai.iston.utils.constants.WordLevelConstants;
import ai.iston.utils.constants.WordLogicConnConstants;
import ai.iston.utils.constants.WordTypeConstants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author: James Zow
 * @Date: 2022/01/18/21:19
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class WordProcessingServiceImpl implements WordProcessingService {

    private final VocabLevelMapper wordLevelMapper;

    private final VocabClassificationDaoService vocabClassificationDaoService;

    private final VocabLogicConnectionDaoService vocabLogicConnectionDaoService;

    @Override
    public List<String> getWordByPartOfSpeech(String article, String part) {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.depparse));

        if (StringUtils.hasText(article)) {
            List<WordBo> words = assembleWordData(article, pipeline);
            // 匹配
            List<WordBo> wordList = words.stream().filter(word -> word.getWordType().equals(part)).collect(toList());
            return wordList.stream().map(WordBo::getWord).collect(toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<WordBo> getWordByWordParsing(String article) {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.lemma));
        if (StringUtils.hasText(article)) {
            return assembleWordData(article, pipeline);
        }
        return new ArrayList<>();
    }

    @Override
    public List<SentenceWordVo> getSentenceAndWordInfo(String article) {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.lemma));
        List<SentenceWordVo> sentenceWordVoList = new ArrayList<>();
        if (StringUtils.hasText(article)) {
            CoreDocument document = pipeline.processToCoreDocument(article);
            int i = 0;
            for (CoreSentence sent : document.sentences()) {

                SentenceWordVo sentenceWordVo = new SentenceWordVo();
                sentenceWordVo.setId(i++);
                sentenceWordVo.setSentence(sent.text());

                List<WordBo> wordBos = sentToWordBo(sent.tokens());
                sentenceWordVo.setWordList(wordBos);
                sentenceWordVoList.add(sentenceWordVo);
            }
        }
        return Optional.of(sentenceWordVoList).orElse(new ArrayList<>());
    }

    @Override
    public WordInfoVo getWordInfo(List<Article> articleList) {
        JSONObject result = new JSONObject();
        if (articleList.isEmpty()) {
            return null;
        }
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.lemma));
        articleList.forEach(item -> {
            List<WordBo> wordBos = assembleWordData(item.getArticle(), pipeline);
            calculateVocabularyLevel(result, wordBos);
            calculateVocabularyDiversity(result, wordBos);
            // 逻辑连接词需要根据句子去寻找
            calculateVocabularyClassification(result, wordBos, item.getArticle());
        });
        return JSONObject.parseObject(String.valueOf(result), WordInfoVo.class);
    }

    @Override
    public List<ParseWordVo> getNVADJADVWord(String article) {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.lemma));
        List<WordBo> wordBos = assembleWordData(article, pipeline);
        return WordModelMapper.INSTANCE.convertParseBosToParseVos(getParsedWords(wordBos));
    }

    /**
     * 组装词汇数据
     *
     * @param article  文章
     * @param pipeline 斯坦福pip
     * @return 返回组装后的数据
     */
    private List<WordBo> assembleWordData(String article, StanfordCoreNLP pipeline) {
        CoreDocument document = pipeline.processToCoreDocument(article);
        return sentToWordBo(document.tokens());
    }

    /**
     * 获取斯坦福分析匹配后 分组的单词
     *
     * @return 分组后的词汇
     */
    private List<ParseWordBo> getParsedWords(List<WordBo> wordVoList) {
        List<ParseWordBo> parseWordBos = new ArrayList<>();
        // 定义4大词性entity说明
        ParseWordBo nounWordBo = new ParseWordBo(), verbWordBo = new ParseWordBo(), adjWordBo = new ParseWordBo(), advWordBo = new ParseWordBo();

        List<String> nounWords = new ArrayList<>(), verbWords = new ArrayList<>(), adjWords = new ArrayList<>(), advWords = new ArrayList<>();

        wordVoList.forEach(word -> {
            if (WordTypeConstants.Companion.getNNWord().contains(word.getWordType())) {
                nounWordBo.setType(WordTypeConstants.NOUNType);
                nounWords.add(word.getLemmaWord());
            } else if (WordTypeConstants.Companion.getVVWord().contains(word.getWordType())) {
                verbWordBo.setType(WordTypeConstants.VERBType);
                verbWords.add(word.getLemmaWord());
            } else if (WordTypeConstants.Companion.getADVWord().contains(word.getWordType())) {
                adjWordBo.setType(WordTypeConstants.ADJType);
                adjWords.add(word.getLemmaWord());
            } else if (WordTypeConstants.Companion.getADJWod().contains(word.getWordType())) {
                advWordBo.setType(WordTypeConstants.ADVType);
                advWords.add(word.getLemmaWord());
            }
        });
        nounWordBo.setWords(nounWords);
        verbWordBo.setWords(verbWords);
        adjWordBo.setWords(adjWords);
        advWordBo.setWords(advWords);
        // 拼装
        parseWordBos.add(nounWordBo);
        parseWordBos.add(verbWordBo);
        parseWordBos.add(adjWordBo);
        parseWordBos.add(advWordBo);

        return parseWordBos;
    }

    private List<WordBo> sentToWordBo(List<CoreLabel> tokens) {
        List<WordBo> wordBos = new ArrayList<>();
        for (CoreLabel word : tokens) {
            if (!".".equals(word.word())) {
                WordBo wordVo = WordBo.builder()
                        .word(word.word())
                        .lemmaWord(word.lemma())
                        .wordType(word.tag())
                        .build();
                wordBos.add(wordVo);
            }
        }
        return wordBos;
    }

    /**
     * 计算词汇等级
     *
     * @param object  要封装的对象
     * @param wordBos 被封装的数据
     */
    private void calculateVocabularyLevel(JSONObject object, List<WordBo> wordBos) {
        JSONObject wordLevelObject = new JSONObject();
        // 还原词性分组的单词
        List<ParseWordBo> parseWordBos = getParsedWords(wordBos);
        // 还原去重词性分组的等级单词
        List<VocabLevelBo> vocabLevelBos = new ArrayList<>();

        parseWordBos.forEach(wordTag -> {
            Map<String, Object> paramMap = new HashMap<>(15);
            paramMap.put("wordType", wordTag.getType());
            paramMap.put("words", wordTag.getWords());
            vocabLevelBos.addAll(WordModelMapper.INSTANCE.convertVocabLevelTosVocabLevelBos(wordLevelMapper.getVocabLevelInfo(paramMap)));
        });
        List<VocabLevelBo> primaryVb = vocabLevelBos.stream().filter(item -> WordLevelConstants.primaryLevel.equals(item.getLevel())).collect(toList());
        List<VocabLevelBo> middleVb = vocabLevelBos.stream().filter(item -> WordLevelConstants.middleLevel.equals(item.getLevel())).collect(toList());
        List<VocabLevelBo> highVb = vocabLevelBos.stream().filter(item -> WordLevelConstants.highLevel.equals(item.getLevel())).collect(toList());

        List<String> wordList = wordBos.stream().map(WordBo::getWord).collect(toList());

        // 总还原
        Long lemmaPrimarySize = wordList.stream().filter(primaryVb.stream().map(VocabLevelBo::getWord).collect(toList())::contains).count();
        Long lemmaMiddleSize = wordList.stream().filter(middleVb.stream().map(VocabLevelBo::getWord).collect(toList())::contains).count();
        Long lemmaHighSize = wordList.stream().filter(highVb.stream().map(VocabLevelBo::getWord).collect(toList())::contains).count();

        // 占比
        BigDecimal primaryPercentage = BigDecimal.valueOf(lemmaPrimarySize.doubleValue() / (lemmaPrimarySize + lemmaMiddleSize + lemmaHighSize) * 100)
                .setScale(1, RoundingMode.HALF_UP);
        BigDecimal middlePercentage = BigDecimal.valueOf(lemmaMiddleSize.doubleValue() / (lemmaPrimarySize + lemmaMiddleSize + lemmaHighSize) * 100)
                .setScale(1, RoundingMode.HALF_UP);
        BigDecimal highPercentage = BigDecimal.valueOf(lemmaHighSize.doubleValue() / (lemmaPrimarySize + lemmaMiddleSize + lemmaHighSize) * 100)
                .setScale(1, RoundingMode.HALF_UP);

        String mainLevel = calculateLevelRule(primaryPercentage, middlePercentage, highPercentage);

        // 拼装等级算法，只组装数据不为空
        JSONArray jsonArray = new JSONArray();

        if(!primaryVb.isEmpty()){
            JSONObject primaryObject = new JSONObject();
            primaryObject.put("level_name", "初级");
            primaryObject.put("percentage", primaryPercentage);
            primaryObject.put("lemma_words", primaryVb.stream().map(VocabLevelBo::getWord).collect(toList()));

            jsonArray.add(primaryObject);
        }
        if(!middleVb.isEmpty()) {
            JSONObject middleObject = new JSONObject();
            middleObject.put("level_name", "中级");
            middleObject.put("percentage", middlePercentage);
            middleObject.put("lemma_words", middleVb.stream().map(VocabLevelBo::getWord).collect(toList()));

            jsonArray.add(middleObject);
        }
        if(!highVb.isEmpty()) {
            JSONObject highObject = new JSONObject();
            highObject.put("level_name", "高级");
            highObject.put("percentage", highPercentage);
            highObject.put("lemma_words", highVb.stream().map(VocabLevelBo::getWord).collect(toList()));

            jsonArray.add(highObject);
        }
        wordLevelObject.put("word_list", jsonArray);
        wordLevelObject.put("result_level", mainLevel);

        object.put("word_level", wordLevelObject);
    }


    /**
     * 计算词汇多样性
     *
     * @param object  要封装的对象
     * @param wordBos 被封装的数据
     */
    private void calculateVocabularyDiversity(JSONObject object, List<WordBo> wordBos) {
        // 还原词性分组的单词
        List<ParseWordBo> parseWordBos = getParsedWords(wordBos);
        // 统计不去重各词性数量
        List<String> nounWords = parseWordBos.stream().filter(m -> WordTypeConstants.NOUNType.equals(m.getType())).collect(toList())
                .stream().map(ParseWordBo::getWords).flatMap(Collection::stream).collect(toList());

        List<String> verbWords = parseWordBos.stream().filter(m -> WordTypeConstants.VERBType.equals(m.getType())).collect(toList())
                .stream().map(ParseWordBo::getWords).flatMap(Collection::stream).collect(toList());

        List<String> adjWords = parseWordBos.stream().filter(m -> WordTypeConstants.ADJType.equals(m.getType())).collect(toList())
                .stream().map(ParseWordBo::getWords).flatMap(Collection::stream).collect(toList());

        List<String> advWords = parseWordBos.stream().filter(m -> WordTypeConstants.ADVType.equals(m.getType())).collect(toList())
                .stream().map(ParseWordBo::getWords).flatMap(Collection::stream).collect(toList());

        // 统计不去重各词性数量
        double nounUnrepeated = nounWords.size(), verbUnrepeated = verbWords.size(), adjUnrepeated = adjWords.size(), advUnrepeated = advWords.size();

        // 去重复
        double nounRepeated = nounWords.stream().distinct().count(), verbRepeated = verbWords.stream().distinct().count(),
                adjRepeated = adjWords.stream().distinct().count(), advRepeated = advWords.stream().distinct().count();

        double nounWeight = 30, verbWeight = 40, adjWeight = 15, advWeight = 15;

        double bigDecimal = BigDecimal.valueOf(nounWeight * (nounRepeated / nounUnrepeated) +
                verbWeight * (verbRepeated / verbUnrepeated) +
                adjWeight * (adjRepeated / adjUnrepeated) +
                advWeight * (advRepeated / advUnrepeated)).setScale(1, RoundingMode.HALF_UP).doubleValue();

        String result = calculateDiversityRule(bigDecimal);
        object.put("word_diversity", result);
    }

    private String calculateLevelRule(BigDecimal primaryPercentage, BigDecimal middlePercentage, BigDecimal highPercentage) {
        String result = "";

        boolean isPrimary = false, isMiddle = false, isHigh = false;
        double l1 = 30.00, l2 = 15.00, l3 = 0.00;

        if (primaryPercentage.doubleValue() >= l1) {
            isPrimary = true;
        }
        if (middlePercentage.doubleValue() >= l2) {
            isMiddle = true;
        }
        if (highPercentage.doubleValue() >= l3) {
            isHigh = true;
        }
        if (isPrimary || isMiddle) {
            result = "初级";
        }
        if (isHigh || isPrimary && isMiddle || isMiddle && isHigh || isPrimary && isHigh) {
            result = "中级";
        }
        if (isPrimary && isMiddle && isHigh) {
            result = "高级";
        }
        return result;
    }

    /**
     * <p>
     * 计算词汇分类
     *
     * @param object  要封装的对象
     * @param wordBos 被封装的数据
     *                </p>
     *                首先斯坦福抓逻辑连贯词 NOUN VERB ADJ ADV 一张Table操作,逻辑连贯词另外一张Table操作.
     */
    private void calculateVocabularyClassification(JSONObject object, List<WordBo> wordBos, String article) {
        JSONArray wordClassificationArray = new JSONArray();

        List<ParseWordBo> parseWordBos = getParsedWords(wordBos);

        List<VocabClassification> vocabClassifications;
        Map<String, List<VocabClassification>> advMap = null, adjMap = null, nounMap = null, verbMap = null;
        Map<String, List<VocabClassification>> adjAndAdvMap = new HashMap<>();

        for (ParseWordBo parseWordBo : parseWordBos) {
            vocabClassifications = vocabClassificationDaoService.getVocalClassificationList(parseWordBo.getType(), parseWordBo.getWords());
            for (VocabClassification item : vocabClassifications) {
                if (WordTypeConstants.ADVType.equals(item.getType())) {
                    advMap = vocabClassifications.stream().collect(Collectors.groupingBy(VocabClassification::getClassification));
                } else if (WordTypeConstants.ADJType.equals(item.getType())) {
                    adjMap = vocabClassifications.stream().collect(Collectors.groupingBy(VocabClassification::getClassification));
                } else if (WordTypeConstants.NOUNType.equals(item.getType())) {
                    nounMap = vocabClassifications.stream().collect(Collectors.groupingBy(VocabClassification::getClassification));
                } else if (WordTypeConstants.VERBType.equals(item.getType())) {
                    verbMap = vocabClassifications.stream().collect(Collectors.groupingBy(VocabClassification::getClassification));
                }
            }
        }
        if (advMap != null && adjMap != null) {
            adjAndAdvMap.putAll(advMap);
            adjAndAdvMap.putAll(adjMap);
            assembleMapData("形容词和副词的分类", wordClassificationArray, adjAndAdvMap);
        }
        if (nounMap != null) {
            assembleMapData("名词的分类", wordClassificationArray, nounMap);
        }
        if (verbMap != null) {
            assembleMapData("动词的分类", wordClassificationArray, verbMap);
        }

        object.put("word_classification", wordClassificationArray);

        // 处理逻辑连接词
        List<VocabLogicConnection> vocabLogicConnections = vocabLogicConnectionDaoService.getVocalLogicConnectionList();
        assembleLogicConnection(article, wordClassificationArray, vocabLogicConnections);
    }

    public static void main(String[] args) {
        double a = 0.04 * 100;
        System.out.println(BigDecimal.valueOf(a).setScale(1, RoundingMode.HALF_UP).doubleValue());
    }

    private void assembleMapData(String name, JSONArray dataArray, Map<String, List<VocabClassification>> listMap) {
        JSONObject object = new JSONObject();
        object.put("name", name);

        double wordTotal = 0;
        for (String key : listMap.keySet()) {
            wordTotal += listMap.get(key).stream().map(VocabClassification::getWord).count();
        }

        JSONArray array = new JSONArray();
        for (String key : listMap.keySet()) {
            List<String> words = listMap.get(key).stream().map(VocabClassification::getWord).collect(Collectors.toList());
            double word = words.size();

            JSONObject objects = new JSONObject();
            objects.put("label", key);
            objects.put("words", words);
            objects.put("percentage", calculatePercentage(word, wordTotal));
            array.add(objects);
        }
        object.put("word_list", array);
        dataArray.add(object);
    }

    private void assembleLogicConnection(String article, JSONArray dataArray, List<VocabLogicConnection> vocabLogicConnections) {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.pos));
        CoreDocument doc = new CoreDocument(article);
        pipeline.annotate(doc);

        List<String> logic1List = new ArrayList<>(), logic2List = new ArrayList<>(), logic3List = new ArrayList<>();

        for (CoreSentence sent : doc.sentences()) {
            for (VocabLogicConnection logic : vocabLogicConnections) {
                if (sent.text().contains(logic.getLogicWords())) {
                    if (logic.getLogicId() == 1) {
                        logic1List.add(logic.getLogicWords());
                    } else if (logic.getLogicId() == 2) {
                        logic2List.add(logic.getLogicWords());
                    } else if (logic.getLogicId() == 3) {
                        logic3List.add(logic.getLogicWords());
                    }
                }
            }
        }
        List<String> logicTurn = logic1List.stream().distinct().collect(toList()), logicFollow = logic2List.stream().distinct().collect(toList()),
                logicProgressive = logic3List.stream().distinct().collect(toList());

        JSONArray wordListArray = new JSONArray();
        JSONObject logicTurnObj = new JSONObject(), logicFollowObj = new JSONObject(), logicProgressiveObj = new JSONObject();
        double totalWord = logicTurn.size() + logicFollow.size() + logicProgressive.size();

        if(!logicTurn.isEmpty()){
            logicTurnObj.put("label", WordLogicConnConstants.Logic1);
            logicTurnObj.put("words", logicTurn);
            logicTurnObj.put("percentage", calculatePercentage(logicTurn.size(), totalWord));

            wordListArray.add(logicTurnObj);
        }
        if(!logicFollow.isEmpty()){
            logicFollowObj.put("label", WordLogicConnConstants.Logic2);
            logicFollowObj.put("words", logicFollow);
            logicFollowObj.put("percentage", calculatePercentage(logicFollow.size(), totalWord));

            wordListArray.add(logicFollowObj);
        }
        if(!logicProgressive.isEmpty()){
            logicProgressiveObj.put("label", WordLogicConnConstants.Logic3);
            logicProgressiveObj.put("words", logicProgressive);
            logicProgressiveObj.put("percentage", calculatePercentage(logicProgressive.size(), totalWord));

            wordListArray.add(logicProgressiveObj);
        }

        // 组装
        JSONObject object = new JSONObject();
        object.put("name", "逻辑连接词的分类");
        object.put("word_list", wordListArray);

        dataArray.add(object);
    }

    private double calculatePercentage(double v1, double v2){
        return BigDecimal.valueOf(v1 / v2 * 100).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    private String calculateDiversityRule(double bigDecimal) {
        double level1Value = 50.00, leve2Value = 70.00;
        String result;

        if (bigDecimal < level1Value) {
            result = "弱";
        } else if (bigDecimal >= level1Value && bigDecimal <= leve2Value) {
            result = "中";
        } else {
            result = "强";
        }
        return result;
    }
}
