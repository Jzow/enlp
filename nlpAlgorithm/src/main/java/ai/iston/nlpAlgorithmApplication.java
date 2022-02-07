package ai.iston;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author James Zow
 * @since 2021/12/28
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"ai.iston.dao.mapper"})
public class nlpAlgorithmApplication {
    public static void main(String[] args) {
        SpringApplication.run(nlpAlgorithmApplication.class, args);
    }
}
