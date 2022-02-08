package ai.james;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author James Zow
 * @since 2021/12/28
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan(basePackages = {"ai.james.dao.mapper"})
public class CorrectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorrectionApplication.class, args);
    }
}
