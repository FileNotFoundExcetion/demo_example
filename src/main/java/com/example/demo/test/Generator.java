package com.example.demo.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
    public static void main(String[] args) {
        System.out.println("输入表名，多个表名之间用逗号隔开：");
        final List<String> tableNames = Collections.singletonList("user");
        final List<String> tables = new ArrayList<>(tableNames);
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/test?useunicode=true&characterencoding=utf8&servertimezone=utc", "root", "root")
                .globalConfig(builder -> {
                    builder.author(System.getProperty("user"))               //作者
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")    //输出路径(写到java目录)
                            // .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> builder.parent("com.example.demo")
                       // .moduleName("demo_example")
                        .entity("entity")
                        .other("")
                        .service("service")
                        .serviceImpl("serviceImpl")
                        //.controller("controller")
                        .mapper("mapper")
                        .xml("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper")))
                .strategyConfig(builder -> builder
                        .addInclude(tables)
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .addSuperEntityColumns("tenant_id", "created_time", "updated_time")
                        //  .controllerBuilder()
                        //  .formatFileName("%sController")
                        //  .enableRestStyle()
                        .mapperBuilder()
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper"))
                .templateConfig(TemplateConfig.Builder::build)
              //  .templateEngine(new FreemarkerTemplateEngine())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
