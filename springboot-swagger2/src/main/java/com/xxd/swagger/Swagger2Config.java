package com.xxd.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author scott
 */

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    SecurityScheme securityScheme() {
        return new ApiKey(X_ACCESS_TOKEN, X_ACCESS_TOKEN, "header");
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(X_ACCESS_TOKEN).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("四好农村路管理后台-APIS")
                .version("1.0")
                .description("API接口文档")
                .build();
    }

    @Bean
    public Docket web_api_1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.vkl.admin.controller.sys.login"),
                        RequestHandlerSelectors.basePackage("com.vkl.admin.controller.common")
                        ))
                .paths(Predicates.or(
                        PathSelectors.ant("/admin/**"),
                        PathSelectors.ant("/app_captcha/**")
                        ))
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .groupName("1.登录");
    }

    @Bean
    public Docket web_api_2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.vkl.admin.controller.index")
                ))
                .build()
                .groupName("2.平台主页");
    }

    @Bean
    public Docket business() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.vkl.admin.controller.road")
                ))
                .paths(Predicates.or(
                        /*养护*/
                        PathSelectors.ant("/admin/road_maintain_plan/**"),
                        PathSelectors.ant("/admin/road_maintain_task/**"),
                        PathSelectors.ant("/admin/road_maintain_record/**"),
                        /*巡查*/
                        PathSelectors.ant("/admin/road_patrol_plan/**"),
                        PathSelectors.ant("/admin/road_patrol_task/**"),
                        PathSelectors.ant("/admin/road_patrol_record/**"),
                        PathSelectors.ant("/admin/road_patrol_report_config/**"),
                        PathSelectors.ant("/admin/damage_road_video/**"),
                        /*道路*/
                        PathSelectors.ant("/admin/road_road_route/**"),
                        PathSelectors.ant("/admin/road_road_route_area/**"),

                        /*建设工程road_project_build*/
                        PathSelectors.ant("/admin/road_project_build/**"),
                        /*流量*/
                        PathSelectors.ant("/admin/road_net_pass/**")
                ))
                .build()
                .groupName("3.业务中心");
    }

    private static String X_ACCESS_TOKEN = "X-Access-Token";
}