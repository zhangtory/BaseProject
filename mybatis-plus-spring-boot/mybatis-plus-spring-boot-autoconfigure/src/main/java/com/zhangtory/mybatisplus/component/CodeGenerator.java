package com.zhangtory.mybatisplus.component;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:20
 * @Description: 代码生成器
 */
public class CodeGenerator {

    private DatabaseProperties databaseProperties;

    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    /**
     * 是否覆盖已有文件
     */
    private Boolean fileOverride = false;

    public void setFileOverride(Boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    /**
     * 修改创建人
     */
    private String author = "";

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 根包路径
     */
    private String modulePath = "";

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    /**
     * 生成代码
     * @param tablesName
     * @param modulePath
     * @param author
     * @param fileOverride
     */
    public void create(String tablesName, String modulePath, String author, Boolean fileOverride) {
        this.author = author;
        this.fileOverride = fileOverride;
        create(tablesName, modulePath);
    }

    /**
     * 生成代码
     * @param tablesName
     * @param modulePath
     * @param author
     */
    public void create(String tablesName, String modulePath, String author) {
        this.author = author;
        create(tablesName, modulePath);
    }

    /**
     * 生成代码
     * @param tablesName 需要生成的表的表名，使用英文逗号分割
     * @param modulePath
     */
    public void create(String tablesName, String modulePath) {
        this.modulePath = modulePath;
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        mpg.setGlobalConfig(globalConfig());
        // 数据源配置
        mpg.setDataSource(dataSourceConfig());
        // 包配置
        mpg.setPackageInfo(packageConfig());
        // 自定义配置mapper.xml
        mpg.setCfg(injectionConfig());
        // 配置模板
        mpg.setTemplate(templateConfig());
        // 策略配置
        mpg.setStrategy(strategyConfig(tablesName));

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 全局配置
     * @return
     */
    private GlobalConfig globalConfig() {
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(fileOverride);
        return gc;
    }

    /**
     * 数据源配置
     * @return
     */
    private DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(databaseProperties.getUrl());
        dsc.setDriverName(databaseProperties.getDriverClassName());
        dsc.setUsername(databaseProperties.getUsername());
        dsc.setPassword(databaseProperties.getPassword());
        return dsc;
    }

    /**
     * 包配置
     * @return
     */
    private PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zhangtory");
        pc.setModuleName(modulePath);
        pc.setEntity("model.entity");
        pc.setMapper("dao");
        return pc;
    }

    /**
     * 自定义配置mapper.xml
     * @return
     */
    private InjectionConfig injectionConfig() {
        String projectPath = System.getProperty("user.dir");
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 配置模板
     * @return
     */
    private TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 策略配置
     * @return
     */
    private StrategyConfig strategyConfig(String tablesName) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tablesName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setVersionFieldName("version");
        strategy.setLogicDeleteFieldName("delete");
        return strategy;
    }

}
