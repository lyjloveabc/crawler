package thor.freedom.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import thor.freedom.crawler.sys.SpringContextUtils;

/**
 * Created by Thor on 2018/1/18.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"thor.freedom.crawler.dao.mapper"})
@EnableTransactionManagement
public class Crawler {
    public static void main(String[] args) {
        SpringApplication.run(Crawler.class, args);
    }

    @Bean
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }
}
