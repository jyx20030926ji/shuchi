package com.testvue.testvue.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 添加基本信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.testvue.testvue.controller")) // 替换为你的包名
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Documentation Title") // API 文档标题
                .description("API Documentation Description") // 描述
                .version("1.0") // 版本
                .contact(new Contact("Your Name", "yourwebsite.com", "youremail@example.com")) // 联系信息
                .build();
    }
}
