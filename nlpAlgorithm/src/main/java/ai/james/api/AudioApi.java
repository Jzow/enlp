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

import ai.james.service.AudioService;
import ai.james.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: James Zow
 * @Date: 2022/01/13/20:44
 * @Description:
 */
@RestController
@Api(tags = "音频算法")
@RequestMapping("audioApi")
@RequiredArgsConstructor
public class AudioApi {

    private final AudioService audioService;

    @PostMapping(value = "audioRecognition")
    @ApiOperation(value = "语音识别（语音转文本）")
    public Result<String> fileUpload(@RequestPart(value= "file") MultipartFile file) {
        String result = audioService.readAudioToText(file);
        if(!StringUtils.hasText(result)){
            return Result.failed("音频识别失败");
        }
        return Result.success(result);
    }
}
