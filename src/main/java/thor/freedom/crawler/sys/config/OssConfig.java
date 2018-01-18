package thor.freedom.crawler.sys.config;

import com.aliyun.oss.OSSClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Thor on 2018/1/18.
 */
//@Configuration
@PropertySource(value = {"classpath:oss.properties"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "oss")
public class OssConfig {
    @Setter
    @Getter
    String endpoint;

    @Setter
    @Getter
    String accessKeyId;

    @Setter
    @Getter
    String accessKeySecret;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
