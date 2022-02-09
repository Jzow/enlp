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
package ai.james.dao.mapper;

import ai.james.model.entity.CorrectionModule;
import ai.james.model.entity.PubWorlds;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 批改模块 Mapper 接口
 */
public interface CorrectionModuleMapper extends BaseMapper<CorrectionModule> {

    List<PubWorlds> matchVocabularyData(Map<String, Object> wordsBoMap);

}
