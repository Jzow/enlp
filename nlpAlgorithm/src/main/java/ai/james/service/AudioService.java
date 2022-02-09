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

import org.springframework.web.multipart.MultipartFile;

/**
 * 语音 服务接口
 */
public interface AudioService {

    /**
     * 音频文件识别
     * @param file 文件
     * @return 返回识别后的结果
     */
    String readAudioToText(MultipartFile file);
}
