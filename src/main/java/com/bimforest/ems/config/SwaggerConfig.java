package com.bimforest.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import java.util.List;

/**
 * @author max.zhang
 * @title: SwaggerConfig
 * @projectName bimforest
 * @description: Swagger2配置类
 * @date 2019/9/119:09
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 　* @description: 创建API应用
     * 　* @author max.zhang
     * 　* @date 2019/9/11 9:11
     * 　* @Copyright (C) 量树科技
     */
    @Bean
    public Docket adminApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Token").description("user ticket")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                // 增加API相关信息
                .apiInfo(apiInfo("so-api", "后台", "1.0"))
                // 返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .select()
                //扫描的包路径来定义指定要建立API的目录
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.sys"))
                .paths(PathSelectors.any())
                .build()
                .groupName("后台管理:adminApi接口文档V1.0");

    }

    /**
     * 工程管理
     * @return
     */
    @Bean
    public Docket construction() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("so-api", "工程管理", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.construction"))
                .paths(PathSelectors.any())
                .build()
                .groupName("工程管理:webApi接口文档V1.0");
    }

    /**
     * 公司部门
     * @return
     */
    @Bean
    public Docket department() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("so-api", "部门管理", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.department"))
                .paths(PathSelectors.any())
                .build()
                .groupName("部门管理:webApi接口文档V1.0");
    }


    /**
     * 项目管理中心
     * @return
     */
    @Bean
    public Docket management() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("so-api", "项管中心", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.management"))
                .paths(PathSelectors.any())
                .build()
                .groupName("项管中心:webApi接口文档V1.0");
    }


    /**
     * 领导页面
     * @return
     */
    @Bean
    public Docket leader() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("so-api", "领导页面", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.leader"))
                .paths(PathSelectors.any())
                .build()
                .groupName("领导页面:webApi接口文档V1.0");
    }



    /**
     * 系统日志
     * @return
     */
    @Bean
    public Docket log() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("so-api", "系统日志", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.log"))
                .paths(PathSelectors.any())
                .build()
                .groupName("系统日志:webApi接口文档V1.0");
    }
    /**
     * web基础信息
     * @return
     */
    @Bean
    public Docket base() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("so-api", "基本信息", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimforest.ems.modules.base"))
                .paths(PathSelectors.any())
                .build()
                .groupName("基本信息:webApi接口文档V1.0");
    }

    /**
     * 　* @description: 创建该API的基本信息（这些基本信息会展现在文档页面中）,访问地址：http://项目实际地址/swagger-ui.html
     * 　* @author max.zhang
     * 　* @date 2019/9/11 9:12
     * 　* @Copyright (C) 量树科技
     *
     *
     * @param s
     * @param 站运营
     * @param s1
     */
    private ApiInfo apiInfo(String s, String 站运营, String s1) {
        return new ApiInfoBuilder()
                .title("量树使用Swagger2构建RESTful APIs")
                .version("1.0")
                .build();
    }
}
