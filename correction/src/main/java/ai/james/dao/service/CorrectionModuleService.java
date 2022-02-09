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

import ai.james.model.entity.CorrectionModule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 批改模块 服务类
 */
public interface CorrectionModuleService extends IService<CorrectionModule> {

    boolean insertBatchCorrectionModuleDataInfo(List<CorrectionModule> correctionModuleList);

    CorrectionModule queryCorrectionModuleByCorrectionId(String correctionId);
}
