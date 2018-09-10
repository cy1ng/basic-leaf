package com.rexlin600.leaf.config;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("分布式ID接口管理" )
                .description("基于Twitter雪花算法生成唯一ID" )
                .license("Apache License Version 2.0" )
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html" )
                .version("1.0.0-SNAPSHOT" )
                .build();
    }

    @Bean
    public Docket idApi() {
        // exclude-path处理
        List<Predicate<String>> excludePath = new ArrayList<>();
        excludePath.add(PathSelectors.ant("/error" ));
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("id-generator" )
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/.*" ))
                .paths(Predicates.not(Predicates.or(excludePath)))
                .build();
    }
}
