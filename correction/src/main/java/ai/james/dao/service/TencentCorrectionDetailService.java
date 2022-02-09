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
package ai.james.dao.service;

import ai.james.model.entity.TencentCorrectionDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
public interface TencentCorrectionDetailService extends IService<TencentCorrectionDetail> {

    /**
     * 查询批改详情数据
     * @param tencentCorrectionId 根据批改id
     * @return 返回 批改详情数据
     */
    List<TencentCorrectionDetail> queryTencentCorrectionDetailList(String tencentCorrectionId);

    /**
     * 批量插入批改详情数据
     * @param tencentCorrectionDetails 批改详情传输对象
     * @return 是否插入成功 true 成功 false 失败
     */
    boolean insertBatchTencentCorrectionDetailData(List<TencentCorrectionDetail> tencentCorrectionDetails);
}
