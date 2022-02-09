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


import ai.james.dao.mapper.CorrectionMapper;
import ai.james.dao.service.CorrectionService;
import ai.james.model.entity.Correction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 全自动批改表 服务实现类
 */
@Service
@RequiredArgsConstructor
public class CorrectionServiceImpl extends ServiceImpl<CorrectionMapper, Correction> implements CorrectionService {

    private final CorrectionMapper correctionMapper;

    @Override
    public boolean insertBatchCorrectionDataInfo(List<Correction> correctionList) {
        return saveBatch(correctionList);
    }

    @Override
    public Correction querySysCorrection(Correction correction) {
        return lambdaQuery()
                .eq(StringUtils.hasText(correction.getClassId()), Correction::getClassId, correction.getClassId())
                .eq(StringUtils.hasText(correction.getHomeworkId()), Correction::getHomeworkId, correction.getHomeworkId())
                .eq(StringUtils.hasText(correction.getId()), Correction::getId, correction.getId())
                .eq(StringUtils.hasText(correction.getUserId()), Correction::getUserId, correction.getUserId())
                .one();
    }
}
