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
package ai.james.facade;

import ai.james.config.FeignMultipartConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 音频和文本服务 外部接口 nacos上的服务
 */
@FeignClient(value = "python-algorithm-service", configuration = FeignMultipartConfig.class, url = "http://192.168.1.66:8000/")
public interface AudioToTextService {

    /**
     * 语音识别 音频 -> 文本
     * @param file 单个文件
     * @return 语音识别文本
     */
    @PostMapping(value = "/audio/audioRecognition", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String readAudioFile(@RequestPart("file") MultipartFile file);
}
