package thor.freedom.crawler.sys.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Thor on 2018/1/18.
 */
//@Configuration
@EnableSwagger2
@Profile(value = {"local"})
public class SwaggerConfig {
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("thor.freedom.crawlerStrategy.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Lists.newArrayList(
                        new ParameterBuilder()
                                .name("token")
                                .description("鉴权用的token")
                                .modelRef(new ModelRef("string"))
                                .parameterType("query")
                                .required(true)
                                .build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("G15 Project API")
                .description("any problem cloud ask back-end coder")
                .version("1.0")
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("罗宣", "", ""))
                .build();
    }
}
