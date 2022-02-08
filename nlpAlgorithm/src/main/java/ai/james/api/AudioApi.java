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
