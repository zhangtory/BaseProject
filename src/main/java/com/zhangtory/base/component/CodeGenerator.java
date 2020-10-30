package com.zhangtory.base.component;

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

    /**************************************************
     *                   配置生成表                   *
     **************************************************/

    /**
     * 需要生成的表的表名，使用英文逗号分割
     */
    private static final String TABLE_NAME = "lm_refund_log";

    /**
     * 是否覆盖已有文件
     */
    private static final Boolean FILE_OVERRIDE = false;

    /**
     * 修改创建人
     */
    private static final String AUTHOR = "ZhangTory";

    /**************************************************
     *           以下配置在创建项目时修改             *
     **************************************************/

    private static final String MODULE_NAME = "base";

    private static final String DATABASE_URL = "jdbc:mysql://192.168.6.123:3306/stock_plat?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai";

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    private static final String DATABASE_USERNAME = "stock_plat";

    private static final String DATABASE_PASSWORD = "123456";

    /**************************************************
     *            生成前请检查以上配置                *
     **************************************************/

    public static void main(String[] args) {
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
        mpg.setStrategy(strategyConfig());

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    /**
     * 全局配置
     * @return
     */
    private static GlobalConfig globalConfig() {
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setFileOverride(FILE_OVERRIDE);
        return gc;
    }

    /**
     * 数据源配置
     * @return
     */
    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATABASE_URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(DATABASE_USERNAME);
        dsc.setPassword(DATABASE_PASSWORD);
        return dsc;
    }

    /**
     * 包配置
     * @return
     */
    private static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zhangtory");
        pc.setModuleName(MODULE_NAME);
        pc.setEntity("model.entity");
        pc.setMapper("dao");
        return pc;
    }

    /**
     * 自定义配置mapper.xml
     * @return
     */
    private static InjectionConfig injectionConfig() {
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
    private static TemplateConfig templateConfig() {
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
    private static StrategyConfig strategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAME.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setVersionFieldName("version");
        strategy.setLogicDeleteFieldName("delete");
        return strategy;
    }

}
