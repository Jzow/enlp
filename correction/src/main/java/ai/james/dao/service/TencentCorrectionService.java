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

import ai.james.model.dto.QueryPageTencentReportDTO;
import ai.james.model.entity.TencentCorrection;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface TencentCorrectionService extends IService<TencentCorrection> {

    /**
     * 插入数据到腾讯批改表
     * @param tencentCorrection 腾讯批改数据传输对象
     * @return 返回是否插入数据成功
     */
    int insertTencentCorrectionData(TencentCorrection tencentCorrection);

    /**
     * 批量插入数据到腾讯批改表
     * @param tencentCorrectionList 腾讯批改传输集合对象
     * @return 返回是否插入数据成功
     */
    boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionList);

    /**
     * 查询报告列表
     * @param queryPageTencentReportDTO 分页查询对象
     * @return 返回批改报告列表
     */
    Page<TencentCorrection> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO);
}
