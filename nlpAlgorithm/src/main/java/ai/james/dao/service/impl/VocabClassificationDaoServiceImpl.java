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
package ai.james.dao.service.impl;

import ai.james.dao.mapper.VocabClassificationMapper;
import ai.james.dao.service.VocabClassificationDaoService;
import ai.james.model.entity.VocabClassification;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/13:58
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class VocabClassificationDaoServiceImpl extends ServiceImpl<VocabClassificationMapper, VocabClassification> implements VocabClassificationDaoService {

    private final VocabClassificationMapper vocabClassificationMapper;

    @Override
    public List<VocabClassification> getVocalClassificationList(String type, List<String> wordList) {
        return lambdaQuery()
                .eq(StringUtils.hasText(type), VocabClassification::getType, type)
                .in(!wordList.isEmpty(), VocabClassification::getWord, wordList)
                .list();

    }
}
