package ai.iston.serviceImpl;

import ai.iston.facade.AudioToTextService;
import ai.iston.service.AudioService;
import ai.iston.utils.FileMultipartFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;

/**
 * @Author: James Zow
 * @Date: 2022/01/14/10:29
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class AudioServiceImpl implements AudioService {

    public final AudioToTextService audioToTextService;

    /**
     * 转换16000Hz 采样率
     * @param sourceFile 资源文件
     * @return 返回转换后的 wav
     */
    public MultipartFile transformWav16000HzAudio(MultipartFile sourceFile) {
        File targetFile = new File("audio.wav");
        try {
            AudioAttributes audio = new AudioAttributes();
            audio.setSamplingRate(16000);
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setOutputFormat("wav");
            attrs.setAudioAttributes(audio);
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(FileMultipartFileUtil.multipartFileToFile(sourceFile)), targetFile, attrs);
            return FileMultipartFileUtil.fileToMultipartFile(targetFile);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            // 文件删除 audio.wav
            targetFile.delete();
        }
    }

    @Override
    public String readAudioToText(MultipartFile file) {
        try {
            MultipartFile resultFile = transformWav16000HzAudio(file);
            String result = audioToTextService.readAudioFile(resultFile);
            System.out.println(result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
