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
package ai.james.dao.mdelmapper;

import ai.james.model.entity.TencentCorrection;
import ai.james.model.vo.TencentCorrectionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 腾讯批改Mapper mapstruct 转换接口
 */
@Mapper
public interface TencentCorrectionModelMapper {

    TencentCorrectionModelMapper INSTANCE = Mappers.getMapper(TencentCorrectionModelMapper.class);

    /**
     * entity -> vo
     * @param tencentCorrection entity
     * @return vo
     */
    TencentCorrectionVO convertEntityToVo(TencentCorrection tencentCorrection);

    /**
     * Page<TencentCorrection> -> Page<TencentCorrectionVO>
     * @param tencentCorrectionPage entity page 对象
     * @return 返回批改报告分页集合数据
     */
    Page<TencentCorrectionVO> convertPageVo(Page<TencentCorrection> tencentCorrectionPage);
}
