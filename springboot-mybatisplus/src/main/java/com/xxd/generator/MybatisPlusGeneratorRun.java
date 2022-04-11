package com.xxd.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 3.5.1版本   mysql正常  但是oracle 驱动匹配异常
 *
 * @Author xxd
 * @Date 2021/12/11
 * @Version 1.0
 */
public class MybatisPlusGeneratorRun {
    public static void main(String[] args) {
        // 该odbc6驱动不支持
        // String url = "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(port=1521)(host=127.0.0.1))(connect_data=(service_name=orcl)(allowMultiQueries=true)))";
        // String username = "archives";
        // String password = "vkeline";

        String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "root";

        String author = "xxd";
        String outputDir = "D:\\JAVAPROJECT\\project-template\\springboot-mybatisplus\\src\\main\\java";
        String parentPackage = "com.xxd";
        String moduleName = "generator";
        String mapperXmlOutputDir = "D:\\JAVAPROJECT\\project-template\\springboot-mybatisplus\\src\\main\\resources\\mapper";
        List<String> tableNames = new LinkedList<>();
        tableNames.add("user");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            // .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlOutputDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames); // 设置需要生成的表名
                    builder.entityBuilder()
                            // .enableLombok() //开启lombok
                            // .enableTableFieldAnnotation() //开启字段注解，开启swagger就不需要了
                    // .idType(IdType.AUTO) // 主键类型
                    // .logicDeleteColumnName("deleteFlag") // 逻辑删除字段名（数据库）
                    ;
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER);//禁用controller模板
                    builder.disable(TemplateType.SERVICE);//禁用service模板
                    builder.disable(TemplateType.SERVICEIMPL);//禁用serviceImpl模板
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
