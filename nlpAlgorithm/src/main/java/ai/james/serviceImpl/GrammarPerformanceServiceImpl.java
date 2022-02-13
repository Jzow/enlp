/*
 * MIT License
 *
 * Copyright (c) 2021 James Zow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software
 */
package ai.james.serviceImpl;

import ai.james.model.entity.Article;
import ai.james.model.vo.GrammarInfoVo;
import ai.james.service.GrammarPerformanceService;
import ai.james.utils.PropertiesFactory;
import ai.james.utils.constants.AnnotatorConstants;
import ai.james.utils.constants.RegexConstants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 语法表现 接口服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GrammarPerformanceServiceImpl implements GrammarPerformanceService {

    @Override
    public GrammarInfoVo getGrammarInfo(List<Article> articleList) {
        JSONObject resultObj = new JSONObject();
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.ner));
        articleList.forEach(item -> {
            CoreDocument document = pipeline.processToCoreDocument(item.getArticle());
            resultObj.put("grammar_performance", grammarPerformance(document.sentences()));
        });
        System.out.println(resultObj);
        return JSONObject.parseObject(String.valueOf(resultObj), GrammarInfoVo.class);
    }

    /**
     * 语法表现
     * @param sentences 分句集合
     */
    private JSONObject grammarPerformance(List<CoreSentence> sentences){
        JSONObject resultObj = new JSONObject();
        resultObj.put("sentence_classification", simpleAndComplexSentenceRecognition(sentences));
        return resultObj;
    }

    /**
     * 简单句复杂句识别
     * @param sentences 句子集合
     * @return 返回简单句复杂句
     */
    private JSONArray simpleAndComplexSentenceRecognition(List<CoreSentence> sentences){
        JSONArray simpleAndComplexArray = new JSONArray();
        JSONObject simpleObject = new JSONObject(), complexObject = new JSONObject();

        List<String> clauseList = findClause(sentences),  nonPredicateList = findNonPredicate(sentences), specialStructureLis = findSpecialStructure(sentences);
        List<String> simpleList = new ArrayList<>(), complexList = new ArrayList<>();
        sentences.forEach(item -> {
            if (clauseList.contains(item.text())) {
                complexList.add(item.text());
            } else if(specialStructureLis.contains(item.text())){
                complexList.add(item.text());
            } else {
                boolean include = false;
                List<String> words = Arrays.asList(item.text().split(RegexConstants.empty));
                for (int i = 0; i < nonPredicateList.size(); i++) {
                    if(words.contains(nonPredicateList.get(i))){
                        include = true;
                    }
                }
                if(include){
                    complexList.add(item.text());
                }else {
                    simpleList.add(item.text());
                }
            }
        });

        double simpleSize = simpleList.size(), complexSize = complexList.size();
        simpleObject.put("name","简单句");
        complexObject.put("name","复杂句");

        simpleObject.put("percentage", BigDecimal.valueOf(simpleSize / (simpleSize + complexSize)).setScale(1, RoundingMode.HALF_UP).doubleValue() * 100);
        complexObject.put("percentage", BigDecimal.valueOf(complexSize / (simpleSize + complexSize)).setScale(1, RoundingMode.HALF_UP).doubleValue() * 100);

        simpleObject.put("sentence", simpleList);
        complexObject.put("sentence", complexList);

        simpleAndComplexArray.add(simpleObject);
        simpleAndComplexArray.add(complexObject);

        return simpleAndComplexArray;
    }

    /**
     * 从句识别
     * @param sentences 句子集合
     * @return 返回从句列表
     */
    private List<String> findClause(List<CoreSentence> sentences){
        List<String> clauseList = new ArrayList<>();
        for (CoreSentence sentence : sentences) {
            Set<Constituent> treeConstituents = sentence.constituencyParse().constituents(new LabeledScoredConstituentFactory());
            Tree tree = sentence.constituencyParse();
            for (Constituent constituent : treeConstituents) {
                if (constituent.label() != null && "SBAR".equals(constituent.label().toString())) {
                    log.info("从句成分: {}", tree.getLeaves().subList(constituent.start(), constituent.end() + 1));
                    clauseList.add(sentence.text());
                }
            }
            // 省略从句信号词 目前先用SBAR 成分
        }
        return clauseList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 非谓语识别
     * @param sentences 句子集合
     * @return 返回非谓语列表
     */
    private List<String> findNonPredicate(List<CoreSentence> sentences){
        List<String> nonPredicateList = new ArrayList<>();
        for (CoreSentence sentence : sentences) {
            for (int i = 0; i < sentence.tokens().size(); ++i){
                CoreLabel tok = sentence.tokens().get(i);
                // 非谓语
                if(tok.tag().contains("VBN") || tok.tag().contains("VBG")){
                    nonPredicateList.add(tok.word());
                }else if(tok.tag().contains("VB")){
                    String leftWord = sentence.tokens().get(i + 1).word();
                    if(leftWord.equals("to")){
                        nonPredicateList.add(leftWord + RegexConstants.empty + tok.word());
                    }
                    // 谓语
                }else if(tok.tag().contains("VBD")|| tok.tag().contains("VBP") || tok.tag().contains("VBZ")){

                }
            }
        }
        return nonPredicateList;
    }

    /**
     * 特殊结构
     * @param sentences
     * @return
     */
    private List<String> findSpecialStructure(List<CoreSentence> sentences){
        List<String> specialStructureList = new ArrayList<>();
        for (CoreSentence sentence : sentences) {
            for (int i = 0; i < sentence.tokens().size(); ++i){
                if(sentence.text().equalsIgnoreCase(RegexConstants.howSymbol + RegexConstants.empty + RegexConstants.todoSymbol)){
                    specialStructureList.add(sentence.text());
                }else {
                    for(String whSymbol : RegexConstants.Companion.getWhSymbol()){
                        if(sentence.text().contains(whSymbol + RegexConstants.empty + RegexConstants.todoSymbol)){
                            specialStructureList.add(sentence.text());
                        }else if(sentence.text().contains(whSymbol + RegexConstants.empty + RegexConstants.tobeSymbol)){
                            specialStructureList.add(sentence.text());
                        }
                    }
                }
            }
        }
        return Optional.ofNullable(specialStructureList).orElse(null);
    }

    /**
     * <p>
     *    1. word,word,word 不作为分割条件。
     *    2. 逗号分割前后满足语法规则（主谓宾，主谓，主系表 三者其一）
     * </p>
     *
     * <p>逗号分割 方法
     * @param sentences stanford core sentence list
     * @return 返回 匹配逗号原则 分割的句子
     */
    private List<String> commaDivision(List<CoreSentence> sentences){
        List<String> resultSentence = new ArrayList<>();

        for (CoreSentence sentence : sentences) {
            String sentText = sentence.text();
            if(sentText.contains(RegexConstants.comma)){
                List<String> sentWords = Arrays.asList(sentText.split(RegexConstants.empty));
                for (int i = 0; i < sentWords.size(); i++) {
                    if(sentWords.get(i).equals(RegexConstants.comma)){
                        // 这里 后续需要补充条件2 2022-02-13
                        if(!RegexConstants.comma.equals(sentWords.get(i + 2)) || !RegexConstants.comma.equals(sentWords.get(i - 2))) {
                            resultSentence.addAll(Arrays.asList(sentText.split(RegexConstants.empty)));
                        }
                    }
                }
            }
        }

        return resultSentence;
    }
}
