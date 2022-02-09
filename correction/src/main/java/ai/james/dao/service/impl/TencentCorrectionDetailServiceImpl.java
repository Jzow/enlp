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

import ai.james.dao.mapper.TencentCorrectionDetailMapper;
import ai.james.dao.service.TencentCorrectionDetailService;
import ai.james.model.entity.TencentCorrectionDetail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
@Service
public class TencentCorrectionDetailServiceImpl extends ServiceImpl<TencentCorrectionDetailMapper, TencentCorrectionDetail> implements TencentCorrectionDetailService {

    @Override
    public List<TencentCorrectionDetail> queryTencentCorrectionDetailList(String tencentCorrectionId){
        return lambdaQuery()
                .eq(StringUtils.hasText(tencentCorrectionId), TencentCorrectionDetail::getTencentCorrectionId, tencentCorrectionId)
                .list();
    }

    @Override
    public boolean insertBatchTencentCorrectionDetailData(List<TencentCorrectionDetail> tencentCorrectionDetails) {
        return saveBatch(tencentCorrectionDetails);
    }
}
