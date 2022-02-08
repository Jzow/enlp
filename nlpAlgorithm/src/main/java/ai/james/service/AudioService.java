package ai.james.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: James Zow
 * @Date: 2022/01/14/10:29
 * @Description:
 */
public interface AudioService {

    /**
     * 音频文件识别
     * @param file 文件
     * @return 返回识别后的结果
     */
    String readAudioToText(MultipartFile file);
}
