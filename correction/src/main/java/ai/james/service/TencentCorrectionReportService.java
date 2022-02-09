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
package ai.james.service;

import ai.james.common.enums.FileTypeEnum;
import ai.james.model.dto.QueryPageTencentReportDTO;
import ai.james.model.dto.TencentCorrectionDTO;
import ai.james.model.entity.TencentCorrection;
import ai.james.model.entity.TencentCorrectionDetail;
import ai.james.model.vo.FileUpLoadVO;
import ai.james.model.vo.TencentCorrectionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 腾讯批改报告 服务类
 */
public interface TencentCorrectionReportService {

    /**
     * 插入数据到腾讯批改表
     * @param tencentCorrection 腾讯批改数据传输对象
     * @return
     */
    int insertTencentCorrectionData(TencentCorrection tencentCorrection);

    /**
     * 批量插入数据到腾讯批改表
     * @param tencentCorrectionDTOList
     * @return
     */
    boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionDTOList);

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

    /**
     * 查询报告列表
     * @param queryPageTencentReportDTO 分页查询对象
     * @return 返回批改报告列表
     */
    Page<TencentCorrectionVO> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO);

    /**
     * 一键批改
     * @param tencentCorrectionDTO 批改传入数据对象
     * @return 返回待定 2022-1-11
     */
    String tencentOneTouchCorrection(TencentCorrectionDTO tencentCorrectionDTO);

    /**
     * 文件上传
     * @param file 单个文件
     * @param fileTypeEnum 文件类型枚举
     * @return 返回上传成功的地址
     */
    FileUpLoadVO fileUpload(MultipartFile file, FileTypeEnum fileTypeEnum);
}
