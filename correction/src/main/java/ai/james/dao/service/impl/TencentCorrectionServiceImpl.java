package ai.james.dao.service.impl;
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
import ai.james.dao.mapper.TencentCorrectionMapper;
import ai.james.dao.service.TencentCorrectionService;
import ai.james.model.dto.QueryPageTencentReportDTO;
import ai.james.model.entity.TencentCorrection;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  腾讯批改ORM服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
@Service
@RequiredArgsConstructor
public class TencentCorrectionServiceImpl extends ServiceImpl<TencentCorrectionMapper, TencentCorrection> implements TencentCorrectionService {

    private final TencentCorrectionMapper tencentCorrectionMapper;

    @Override
    public int insertTencentCorrectionData(TencentCorrection tencentCorrection) {
        return tencentCorrectionMapper.insert(tencentCorrection);
    }

    @Override
    public boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionList) {
        return saveBatch(tencentCorrectionList);
    }

    @Override
    public Page<TencentCorrection> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO) {
        return lambdaQuery()
                .orderBy(null != queryPageTencentReportDTO.getSerialNumber(), queryPageTencentReportDTO.getSerialNumber() == null, TencentCorrection::getSerialNumber)
                .orderBy(null != queryPageTencentReportDTO.getTopicModule(), queryPageTencentReportDTO.getTopicModule() == null, TencentCorrection::getTopicModule)
                .orderByDesc(TencentCorrection::getCorrectionTime)
                .page(queryPageTencentReportDTO.buildMybatisPage());
    }
}
