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

import ai.james.dao.mapper.CorrectionModuleMapper;
import ai.james.dao.service.CorrectionModuleService;
import ai.james.model.entity.CorrectionModule;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 批改模块 服务实现类
 */
@Service
@RequiredArgsConstructor
public class CorrectionModuleServiceImpl extends ServiceImpl<CorrectionModuleMapper, CorrectionModule> implements CorrectionModuleService {

    @Override
    public boolean insertBatchCorrectionModuleDataInfo(List<CorrectionModule> correctionModuleList) {
        return saveBatch(correctionModuleList);
    }

    @Override
    public CorrectionModule queryCorrectionModuleByCorrectionId(String correctionId) {
        return lambdaQuery()
                .eq(StringUtils.hasText(correctionId), CorrectionModule::getCorrectionId, correctionId)
                .one();
    }
}
