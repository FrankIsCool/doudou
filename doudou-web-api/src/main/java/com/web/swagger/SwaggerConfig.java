package com.web.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        List<Parameter> params = new ArrayList<>();

        ParameterBuilder token = new ParameterBuilder();
        token.name("token")
                .description("登录令牌")
                .modelRef(new ModelRef("String"))
                .parameterType("header");
        params.add(token.build());

        ParameterBuilder source = new ParameterBuilder();
        source.name("source")
                .description("登录源")
                .modelRef(new ModelRef("String"))
                .parameterType("header");
        params.add(source.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(params)
                .ignoredParameterTypes(HttpServletResponse.class, HttpServletRequest.class);
    }

    /**
     * 项目简介
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("豆豆小助手")
                .description("个人的小管家")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact("frank")
                .version("1.0")
                .build();
    }
}
