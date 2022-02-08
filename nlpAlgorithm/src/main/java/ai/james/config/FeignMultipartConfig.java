package ai.james.config;

import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

/**
 * @Author: James Zow
 * @Date: 2022/01/13/20:58
 * @Description:
 */
public class FeignMultipartConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public SpringFormEncoder feignFormEncoder(){
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
