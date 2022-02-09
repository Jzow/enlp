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

import ai.james.common.enums.FileTypeEnum;
import ai.james.common.result.Result;
import ai.james.common.result.ResultCode;
import ai.james.model.dto.QueryPageTencentReportDTO;
import ai.james.model.dto.TencentCorrectionDTO;
import ai.james.model.vo.FileUpLoadVO;
import ai.james.model.vo.TencentCorrectionVO;
import ai.james.service.TencentCorrectionReportService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: James Zow
 * @Date: 2022/01/10/16:00
 * @Description: 腾讯-全自动批改API
 */
@RequestMapping("tencent")
@RestController
@RequiredArgsConstructor
@Api(tags = "腾讯demo")
public class TencentCorrectionController {

    private final TencentCorrectionReportService tencentService;

    @PostMapping(value = "correction")
    @ApiOperation("批改接口")
    public Result<String> correction(@RequestBody TencentCorrectionDTO tencentCorrectionDTO){
        String resultFlag = tencentService.tencentOneTouchCorrection(tencentCorrectionDTO);
        if(resultFlag == null){
            return Result.failed("批改失败");
        }
        return Result.success(ResultCode.SUCCESS, "批改成功~");
    }

    @PostMapping(value = "queryReportList")
    @ApiOperation("分页查询成绩报告")
    public Result<Page<TencentCorrectionVO>> queryReportList(@RequestBody QueryPageTencentReportDTO queryPageTencentReportDTO){
        Page<TencentCorrectionVO> tencentCorrectionVoPage;
        tencentCorrectionVoPage = tencentService.queryCorrectionListInfoBySort(queryPageTencentReportDTO);
        if(tencentCorrectionVoPage == null){
            return Result.failed("没有查询到对应的数据");
        }
        return Result.success(tencentCorrectionVoPage);
    }

    @ApiOperation("文件上传")
    @PostMapping(value = "fileUpload")
    public Result<FileUpLoadVO> fileUpload(@RequestPart(value= "file") MultipartFile file, @RequestParam(value = "fileTypeEnum")FileTypeEnum fileTypeEnum) {
        FileUpLoadVO fileUpLoadVO = tencentService.fileUpload(file, fileTypeEnum);
        if(fileUpLoadVO == null){
            return Result.failed("文件上传失败，请检查命名格式！");
        }
        return Result.success(fileUpLoadVO);
    }
}
