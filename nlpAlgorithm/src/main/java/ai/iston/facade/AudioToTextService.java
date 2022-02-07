package ai.iston.facade;

import ai.iston.config.FeignMultipartConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: James Zow
 * @Date: 2022/01/13/20:40
 * @Description: 音频和文本服务
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
