package ai.james.service.impl;

import ai.james.common.SnowFlake;
import ai.james.dao.mapper.CorrectionModuleMapper;
import ai.james.dao.service.CorrectionModuleService;
import ai.james.dao.service.CorrectionService;
import ai.james.model.bo.WordsBO;
import ai.james.model.bo.WordsTestBO;
import ai.james.model.dto.CorrectionDTO;
import ai.james.model.dto.QueryCorrectionDTO;
import ai.james.model.entity.Correction;
import ai.james.model.entity.CorrectionModule;
import ai.james.model.entity.PubWorlds;
import ai.james.model.vo.WordLevelVO;
import ai.james.model.vo.WordsInfoVO;
import ai.james.model.vo.WordsTestVO;
import ai.james.service.BasicCorrectionService;
import ai.james.service.NLPAlgorithmService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author James Zow
 * @version 1.0
 * @description: TODO
 * @date 2021/11/23 11:05
 */
@Service
@RequiredArgsConstructor
public class BasicCorrectionServiceImpl implements BasicCorrectionService {

    private final CorrectionService correctionService;

    private final CorrectionModuleService correctionModuleService;

    private final NLPAlgorithmService nlpAlgorithmService;

    private final CorrectionModuleMapper correctionModuleMapper;


    public String correction(CorrectionDTO correctionDTO) {

        List<CorrectionDTO.StudentCorrectionDataDTO> studentAnswer = correctionDTO.getAnswerList();

        JSONArray requestArray = new JSONArray();
        studentAnswer.forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("student_id", item.getStudentId());
            jsonObject.put("student_answer", item.getStudentAnswer());
            requestArray.add(jsonObject);
        });
        System.out.println(requestArray);
        String pythonData = nlpAlgorithmService.writingV2TestAlgorithm(requestArray);
        JSONObject jsonObject = JSON.parseObject(pythonData);

        LocalDateTime localDateTime = LocalDateTime.now();

        if (jsonObject.getInteger("code").equals(200)) {
            JSONArray dataArray = jsonObject.getJSONArray("data");

            List<Correction> correctionList = new ArrayList<>();
            List<CorrectionModule> correctionModuleList = new ArrayList<>();

            for (int i = 0; i < dataArray.size(); ++i) {
                List<PubWorlds> pubWorldsList = new ArrayList<>();
                JSONObject object = dataArray.getJSONObject(i);
                String userId = object.getString("student_id");
                List<WordsBO> wordsList = JSONArray.parseArray(object.getString("word_list"), WordsBO.class);

                for (WordsBO wordsBO : wordsList) {
                    if (!wordsBO.getWords().isEmpty()) {
                        List<String> originalWords = new ArrayList<>();
                        for (WordsBO.Words words : wordsBO.getWords()) {
                            originalWords.add(words.getOriginalWord());
                        }
                        Map<String, Object> objectMap = new HashMap<>(16);
                        objectMap.put("wordType", wordsBO.getWordType());
                        objectMap.put("words", originalWords);
                        List<PubWorlds> foundOriginalWordList = correctionModuleMapper.matchVocabularyData(objectMap);
                        pubWorldsList.addAll(foundOriginalWordList);

                        List<String> onlyList = originalWords.stream().filter(item -> !foundOriginalWordList.stream().map(PubWorlds::getWord)
                                .collect(Collectors.toList()).contains(item)).collect(Collectors.toList());

                        System.out.println(onlyList.toString());

                        if(!onlyList.isEmpty()){
                            // 去找还原词
                            List<String> lemmaWords = new ArrayList<>();
                            for (String word : onlyList) {
                                for (WordsBO.Words words : wordsBO.getWords()) {
                                    if (words.getOriginalWord().equals(word)) {
                                        lemmaWords.add(words.getLemmaWord());
                                    }
                                }
                            }

                            System.out.println(lemmaWords.toString());

                            Map<String, Object> lemmaWordMap = new HashMap<>(16);
                            lemmaWordMap.put("wordType", wordsBO.getWordType());
                            lemmaWordMap.put("words", lemmaWords);
                            pubWorldsList.addAll(correctionModuleMapper.matchVocabularyData(lemmaWordMap));
                        }
                    }
                }

                // 组装数据到批改详情表
                String id = SnowFlake.autoSnowId();
                Correction correction = Correction.builder()
                        .id(id)
                        .homeworkId(correctionDTO.getHomeworkId())
                        .classId(correctionDTO.getClassId())
                        .userId(userId)
                        .status(0)
                        .build();
                correctionList.add(correction);

                JSONArray resultArray = new JSONArray();
                String correctionModuleId = SnowFlake.autoSnowId();
                // 比对完后组装object
                JSONObject primaryObj = new JSONObject();
                List<String> primaryWord = pubWorldsList.stream()
                        .filter(item -> "初级".equals(item.getWordHvcLevel()))
                        .map(PubWorlds::getWord)
                        .collect(Collectors.toList());
                primaryObj.put("level_name", "初级");
                primaryObj.put("word_list", primaryWord);
                double primaryWordNum = primaryWord.size();
                double primaryPercentage = BigDecimal.valueOf(primaryWordNum / pubWorldsList.size() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
                primaryObj.put("percentage", primaryPercentage);

                JSONObject middleObj = new JSONObject();
                List<String> middleWord = pubWorldsList.stream()
                        .filter(item -> "中级".equals(item.getWordHvcLevel()))
                        .map(PubWorlds::getWord)
                        .collect(Collectors.toList());
                middleObj.put("level_name", "中级");
                middleObj.put("word_list", middleWord);
                double middleWordNum = middleWord.size();
                double middlePercentage = BigDecimal.valueOf(middleWordNum / pubWorldsList.size() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
                middleObj.put("percentage", middlePercentage);

                JSONObject highObj = new JSONObject();
                List<String> highWord = pubWorldsList.stream()
                        .filter(item -> "高级".equals(item.getWordHvcLevel()))
                        .map(PubWorlds::getWord)
                        .collect(Collectors.toList());
                highObj.put("level_name", "高级");
                highObj.put("word_list", highWord);
                double highWordNum = highWord.size();
                double highPercentage = BigDecimal.valueOf(highWordNum / pubWorldsList.size() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
                highObj.put("percentage", highPercentage);

                // array封装
                resultArray.add(primaryObj);
                resultArray.add(middleObj);
                resultArray.add(highObj);

                JSONObject dataObject = new JSONObject();
                dataObject.put("word", resultArray);
                CorrectionModule correctionModule = CorrectionModule.builder()
                        .id(correctionModuleId)
                        .topicModule(1)
                        .moduleDetailInfo(String.valueOf(dataObject))
                        .correctionId(id)
                        .build();

                correctionModuleList.add(correctionModule);

            }
            // 组装个人Array
            correctionService.insertBatchCorrectionDataInfo(correctionList);
            correctionModuleService.insertBatchCorrectionModuleDataInfo(correctionModuleList);
            return "批改词汇算法成功";
        } else {
            return "批改词汇算法异常";
        }
    }


    public String correctionTest(CorrectionDTO correctionDTO) {
        List<CorrectionDTO.StudentCorrectionDataDTO> studentAnswer = correctionDTO.getAnswerList();

        JSONArray requestArray = new JSONArray();
        studentAnswer.forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("student_id", item.getStudentId());
            jsonObject.put("student_answer", item.getStudentAnswer());
            requestArray.add(jsonObject);
        });
        System.out.println(requestArray);
        String pythonData = nlpAlgorithmService.V2TestAlgorithm(requestArray);
        JSONObject jsonObject = JSON.parseObject(pythonData);


        if (jsonObject.getInteger("code").equals(200)) {
            JSONArray dataArray = jsonObject.getJSONArray("data");

            List<Correction> correctionList = new ArrayList<>();
            List<CorrectionModule> correctionModuleList = new ArrayList<>();

            for (int i = 0; i < dataArray.size(); ++i) {
                JSONObject resultObject = new JSONObject();
                JSONArray wordInfoArray = new JSONArray();

                JSONObject object = dataArray.getJSONObject(i);
                String userId = object.getString("student_id");

                List<WordsTestBO> wordsList = JSONArray.parseArray(object.getString("word_info"), WordsTestBO.class);

                for (WordsTestBO wordsBO : wordsList) {
                    List<PubWorlds> pubWorldsList = new ArrayList<>();

                    JSONObject wordInfoObject = new JSONObject();
                    wordInfoObject.put("sentence_id", wordsBO.getSentenceId());
                    wordInfoObject.put("sentence", wordsBO.getSentence());

                    for (WordsTestBO.WordList wordList : wordsBO.getWordList()) {
                        if (!wordList.getWords().isEmpty()) {
                            List<String> originalWords = new ArrayList<>();

                            for (WordsTestBO.WordList.Words words : wordList.getWords()) {
                                originalWords.add(words.getOriginalWord());
                            }

                            Map<String, Object> objectMap = new HashMap<>(16);
                            objectMap.put("wordType", wordList.getWordType());
                            objectMap.put("words", originalWords);

                            List<PubWorlds> foundOriginalWordList = correctionModuleMapper.matchVocabularyData(objectMap);
                            pubWorldsList.addAll(foundOriginalWordList);

                            List<String> onlyList = originalWords.stream().filter(item -> !foundOriginalWordList.stream().map(PubWorlds::getWord)
                                    .collect(Collectors.toList()).contains(item)).collect(Collectors.toList());

                            if (!onlyList.isEmpty()) {
                                // 去找还原词
                                List<String> lemmaWords = new ArrayList<>();
                                for (String word : onlyList) {
                                    for (WordsTestBO.WordList.Words words : wordList.getWords()) {
                                        if (words.getOriginalWord().equals(word)) {
                                            lemmaWords.add(words.getLemmaWord());
                                        }
                                    }
                                }

                                Map<String, Object> lemmaWordMap = new HashMap<>(16);
                                lemmaWordMap.put("wordType", wordList.getWordType());
                                lemmaWordMap.put("words", lemmaWords);
                                pubWorldsList.addAll(correctionModuleMapper.matchVocabularyData(lemmaWordMap));

                            }
                        }
                    }
                    // 比对完后组装object
                    JSONArray wordListArray = new JSONArray();

                    List<PubWorlds> primaryWord = pubWorldsList.stream()
                            .filter(item -> "初级".equals(item.getWordHvcLevel()))
                            .collect(Collectors.toList());
                    for (PubWorlds word : primaryWord) {
                        JSONObject primaryObject = new JSONObject();
                        primaryObject.put("word", word.getWord());
                        primaryObject.put("word_type", word.getWordType());
                        primaryObject.put("level_name", "初级");
                        wordListArray.add(primaryObject);
                    }

                    List<PubWorlds> middleWord = pubWorldsList.stream()
                            .filter(item -> "中级".equals(item.getWordHvcLevel()))
                            .collect(Collectors.toList());
                    for (PubWorlds word : middleWord) {
                        JSONObject middleObj = new JSONObject();
                        middleObj.put("word", word.getWord());
                        middleObj.put("word_type", word.getWordType());
                        middleObj.put("level_name", "中级");
                        wordListArray.add(middleObj);
                    }

                    List<PubWorlds> highWord = pubWorldsList.stream()
                            .filter(item -> "高级".equals(item.getWordHvcLevel()))
                            .collect(Collectors.toList());
                    for (PubWorlds word : highWord) {
                        JSONObject highObj = new JSONObject();
                        highObj.put("word", word.getWord());
                        highObj.put("word_type", word.getWordType());
                        highObj.put("level_name", "高级");
                        wordListArray.add(highObj);
                    }

                    wordInfoObject.put("word_list", wordListArray);
                    wordInfoArray.add(wordInfoObject);

                    resultObject.put("nlp", object);
                    resultObject.put("word_info", wordInfoArray);
                }
                // 组装个人Array
                // 组装数据到批改详情表
                String id = SnowFlake.autoSnowId();
                Correction correction = Correction.builder()
                        .id(id)
                        .homeworkId(correctionDTO.getHomeworkId())
                        .classId(correctionDTO.getClassId())
                        .userId(userId)
                        .status(0)
                        .build();
                correctionList.add(correction);

                String correctionModuleId = SnowFlake.autoSnowId();
                CorrectionModule correctionModule = CorrectionModule.builder()
                        .id(correctionModuleId)
                        .topicModule(1)
                        .moduleDetailInfo(String.valueOf(resultObject))
                        .correctionId(id)
                        .build();

                correctionModuleList.add(correctionModule);
            }

            correctionService.insertBatchCorrectionDataInfo(correctionList);
            correctionModuleService.insertBatchCorrectionModuleDataInfo(correctionModuleList);
            return "批改词汇算法成功";
        } else {
            return "批改词汇算法异常";
        }
    }


    public List<WordLevelVO> getLexicalLevel(QueryCorrectionDTO queryCorrectionDTO) {

        Correction correction = Correction.builder()
                .classId(queryCorrectionDTO.getClassId())
                .homeworkId(queryCorrectionDTO.getHomeworkId())
                .userId(queryCorrectionDTO.getUserId())
                .build();
        String correctionId = correctionService.querySysCorrection(correction).getId();

        CorrectionModule correctionModule = correctionModuleService.queryCorrectionModuleByCorrectionId(correctionId);
        JSONObject object = JSONObject.parseObject(correctionModule.getModuleDetailInfo());

        return JSONArray.parseArray(object.getString("word"), WordLevelVO.class);
    }

    public WordsTestVO getLexicalLevelTest(QueryCorrectionDTO queryCorrectionDTO) {
        Correction correction = Correction.builder()
                .classId(queryCorrectionDTO.getClassId())
                .homeworkId(queryCorrectionDTO.getHomeworkId())
                .userId(queryCorrectionDTO.getUserId())
                .build();
        String correctionId = correctionService.querySysCorrection(correction).getId();

        CorrectionModule correctionModule = correctionModuleService.queryCorrectionModuleByCorrectionId(correctionId);
        JSONObject object = JSONObject.parseObject(correctionModule.getModuleDetailInfo());

        return WordsTestVO.builder()
                .nlp(JSONObject.parseObject(object.getString("nlp"), WordsTestVO.Nlp.class))
                .wordsInfoVO(JSONArray.parseArray(object.getString("word_info"), WordsInfoVO.class))
                .build();
    }

    @Override
    public List<WordLevelVO> wordCorrection(CorrectionDTO correctionDTO) {
        correction(correctionDTO);

        QueryCorrectionDTO queryCorrectionDTO = new QueryCorrectionDTO();
        queryCorrectionDTO.setUserId(correctionDTO.getAnswerList().get(0).getStudentId());
        queryCorrectionDTO.setHomeworkId(correctionDTO.getHomeworkId());
        queryCorrectionDTO.setClassId(correctionDTO.getClassId());
        return getLexicalLevel(queryCorrectionDTO);
    }

    @Override
    public WordsTestVO wordCorrectionTest(CorrectionDTO correctionDTO) {
        correctionTest(correctionDTO);

        QueryCorrectionDTO queryCorrectionDTO = new QueryCorrectionDTO();
        queryCorrectionDTO.setUserId(correctionDTO.getAnswerList().get(0).getStudentId());
        queryCorrectionDTO.setHomeworkId(correctionDTO.getHomeworkId());
        queryCorrectionDTO.setClassId(correctionDTO.getClassId());
        return getLexicalLevelTest(queryCorrectionDTO);
    }

}
