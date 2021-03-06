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
package ai.james.service;

import ai.james.model.bo.WordBo;
import ai.james.model.entity.Article;
import ai.james.model.vo.ParseWordVo;
import ai.james.model.vo.SentenceWordVo;
import ai.james.model.vo.WordInfoVo;

import java.util.List;

/**
 * 词汇处理 服务接口
 */
public interface WordProcessingService {

    /**
     * 获取该文章指定词性的单词
     * @param article 文章
     * @param part 词性
     * @return 返回单词
     */
    List<String> getWordByPartOfSpeech(String article, String part);

    /**
     * 获取该文章所有单词和词性
     * @param article 文章
     * @return 单词对象
     */
    List<WordBo> getWordByWordParsing(String article);

    /**
     * 获取句子和单词的数据（每一段句子的单词解析）
     * @param article 文章
     * @return 返回句子单词对象
     */
    List<SentenceWordVo> getSentenceAndWordInfo(String article);

    /**
     * 获取词汇信息（总算法）
     * @param articleList 文章集合
     * @return 返回整个词汇信息
     */
    WordInfoVo getWordInfo(List<Article> articleList);

    /**
     * 获取 文章4大词性的所有单词
     * @param article 文章
     * @return 返回N V ADJ ADV单词
     */
    List<ParseWordVo> getNVADJADVWord(String article);
}
