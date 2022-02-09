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
package ai.james.model.modelmapper;

import ai.james.model.bo.ParseWordBo;
import ai.james.model.bo.VocabLevelBo;
import ai.james.model.bo.WordBo;
import ai.james.model.entity.VocabLevel;
import ai.james.model.vo.ParseWordVo;
import ai.james.model.vo.WordVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * 词汇模型 Mapstruct转换类
 */
@Mapper
public interface WordModelMapper {

    WordModelMapper INSTANCE = Mappers.getMapper(WordModelMapper.class);

    /**
     * bo -> vo
     * @param wordBo bo
     * @return vo
     */
    WordVo convertBoToVo(WordBo wordBo);

    /**
     * bo list -> vo list
     * @param wordBo bos
     * @return vos
     */
    List<WordVo> convertBosToVos(List<WordBo> wordBo);

    /**
     * parseWordBo -> parseWordVo
     * @param parseWordBo 转换bo
     * @return parseWordVo
     */
    ParseWordVo convertParseBoToParseVo(ParseWordBo parseWordBo);

    /**
     * parseWordBos -> parseWordVos
     * @param parseWordBos bos
     * @return vos
     */
    List<ParseWordVo> convertParseBosToParseVos(List<ParseWordBo> parseWordBos);

    /**
     * vocabLevel -> VocabLevelBo
     * @param vocabLevel entity
     * @return VocabLevel bo
     */
    VocabLevelBo convertVocabLevelToVocabLevelBo(VocabLevel vocabLevel);

    /**
     * vocabLevels -> VocabLevelBos
     * @param vocabLevels entity list
     * @return VocabLevel bo list
     */
    List<VocabLevelBo> convertVocabLevelTosVocabLevelBos(List<VocabLevel> vocabLevels);
}
