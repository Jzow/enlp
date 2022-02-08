package ai.james.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/22:06
 * @Description:
 */
@ConfigurationProperties("com.prop")
@Component
@Data
public class ComProperties {

    /**
     * 文件服务上传地址
     */
    private String uploadPath;
}
