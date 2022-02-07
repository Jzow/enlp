package ai.iston;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.info.MultimediaInfo;

import java.io.File;
import java.math.BigDecimal;

/**
 * @Author: James Zow
 * @Date: 2022/01/13/23:05
 * @Description: 音频文件转换测试
 */
@Slf4j
public class AudioTransformTest {

    public static void main(String[] args) throws Exception {
        test02();
    }

    public static void test01() throws Exception, InputFormatException, EncoderException {
        File source = new File("C:\\Users\\Administrator\\Desktop\\yasi.mp3");
        File target = new File("C:\\Users\\Administrator\\Desktop\\test2.wav");

        AudioAttributes audio = new AudioAttributes();
        audio.setSamplingRate(16000);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("wav");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attrs);

    }

    public static void test02() throws EncoderException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\wang.wav");
        MultimediaObject instance = new MultimediaObject(file);
        MultimediaInfo result = instance.getInfo();

        log.info(String.format("视频大小（Byte）:%s", file.length()));
        log.info(String.format("视频大小（KB）:%s", new BigDecimal(file.length() + "").divide(new BigDecimal("1024"), BigDecimal.ROUND_UP).doubleValue()));
        log.info(String.format("视频真实格式:%s", result.getFormat()));
        log.info(String.format("视频时长（毫秒）:%s", result.getDuration()));
        log.info(String.format("视频宽：%s，高:%s", result.getVideo().getSize().getWidth(), result.getVideo().getSize().getHeight()));
        log.info(String.format("视频比特率（bit rate）:%s", result.getVideo().getBitRate()));
        log.info(String.format("视频信息:%s", JSON.toJSONString(result.getMetadata())));
        log.info(String.format("视频Video信息:%s", JSON.toJSONString(result.getVideo())));
        log.info(String.format("视频Audio信息:%s", JSON.toJSONString(result.getAudio())));
    }
}
