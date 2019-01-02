package com.orange;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     *配置swagger2基本内容,扫描包等
     */
    @Bean
    public Docket createRestApi(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder useridPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("authToken").description("用户令牌").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        useridPar.name("authUserId").description("用户id").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        pars.add(useridPar.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.orange.controller"))
                .paths(PathSelectors.any()).build().globalOperationParameters(pars);
    }

    /**
     * 构建api文档信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("orange短视频api")
                .contact(new Contact("tjx","http://www.orange.com","954894026@qq.com"))
                .description("供前端人员使用的api接口文档")
                .version("1.0").build();
    }
}
