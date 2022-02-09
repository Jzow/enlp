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


import ai.james.model.dto.CorrectionDTO;
import ai.james.model.vo.WordLevelVO;
import ai.james.model.vo.WordsTestVO;

import java.util.List;

/**
 * 通用批改 服务类
 */
public interface BasicCorrectionService {

    /**
     * 获取词汇
     * @param correctionDTO
     * @return 返回组装比对好的词汇数据
     */
    List<WordLevelVO> wordCorrection(CorrectionDTO correctionDTO);

    /**
     * 获取词汇测试
     * @param correctionDTO
     * @return 返回组装比对好的词汇数据
     */
    WordsTestVO wordCorrectionTest(CorrectionDTO correctionDTO);
}
