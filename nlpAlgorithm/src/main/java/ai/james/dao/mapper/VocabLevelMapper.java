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

import ai.james.model.entity.VocabLevel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
public interface VocabLevelMapper {

    /**
     * 获取词性等级
     * @param wordsBoMap 组装请求数据
     * @return 返回数据
     */
    List<VocabLevel> getVocabLevelInfo(Map<String, Object> wordsBoMap);

}
