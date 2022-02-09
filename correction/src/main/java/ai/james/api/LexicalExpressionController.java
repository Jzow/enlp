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
package ai.james.api;

import ai.james.common.result.Result;
import ai.james.model.dto.CorrectionDTO;
import ai.james.model.vo.WordLevelVO;
import ai.james.model.vo.WordsTestVO;
import ai.james.service.BasicCorrectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "lexicalExpression")
@Api(tags = "原型算法批改")
@RequiredArgsConstructor
public class LexicalExpressionController {

    private final BasicCorrectionService correctionService;

    @PostMapping(value = "lexicalCorrection")
    @ApiOperation("原型批改-饼状图")
    public Result<List<WordLevelVO>> lexicalCorrection(@RequestBody CorrectionDTO correctionDTO){
        List<WordLevelVO> wordLevelVOList = correctionService.wordCorrection(correctionDTO);
        if(wordLevelVOList.isEmpty()){
            return Result.failed("获取失败");
        }
        return Result.success(wordLevelVOList);
    }

    @PostMapping(value = "lexicalCorrectionTest")
    @ApiOperation("原型测试批改-表格")
    public Result<WordsTestVO> lexicalCorrectionTest(@RequestBody CorrectionDTO correctionDTO){
        WordsTestVO wordTestVO = correctionService.wordCorrectionTest(correctionDTO);
        if(wordTestVO == null){
            return Result.failed("获取失败");
        }
        return Result.success(wordTestVO);
    }


}
