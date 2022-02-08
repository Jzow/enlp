package ai.james;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author pengmf
 * @since 2021/9/22
 */
public class CodeGenerator {

    /**
     * 读取控制台内容
     */

    public static void main(String[] args) {

        String author="James Zow";
        String table []=new String[]{"tencent_correction", "tencent_correction_detail"};

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/correction/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setServiceName("%sService");

        //   jdbc:mysql://139.196.185.136:3306/aiedu?characterEncoding=UTF-8
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.196.185.136:3306/iston_core?characterEncoding=UTF-8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("iston123456!~");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("dao");
        pc.setParent("ai.iston");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude(table);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        mpg.setTemplate(templateConfig);

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }



}
