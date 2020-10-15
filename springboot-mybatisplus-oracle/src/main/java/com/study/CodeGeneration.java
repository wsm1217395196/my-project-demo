package com.study;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis plus3.x 代码生成器
 *
 * @author wsm
 * @date 2019-10-22
 * <p>
 */
public class CodeGeneration {

    /**
     * @param args
     * @Title: main
     * @Description: 生成
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //生成位置
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setOutputDir("C:\\Users\\cy\\Desktop\\my-project\\my-project-demo\\springboot-mybatisplus-oracle\\src\\main\\java");
//        gc.setOutputDir("C:\\Users\\cy\\Desktop\\codeGeneration");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(true);// XML columList
        gc.setOpen(true); //打开生成文件位置
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setAuthor("wsm");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sServiceI");
        gc.setServiceImplName("%sService");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setEntityName("%sModel");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setDriverName("oracle.jdbc.OracleDriver");
        dsc.setUsername("phsms");
        dsc.setPassword("phsms_2019");
        dsc.setUrl("jdbc:oracle:thin:@120.77.199.11:15221:HELOWIN");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{""});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(new String[] {"T_DATA_PLANNING_FAMILY"}); // 需要生成的表
//        strategy.setInclude("user"); // 需要生成的表
//        strategy.setInclude(new String[]{}); // 需要生成的表
        strategy.setEntityLombokModel(true); //lombok
//        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true); //@RestController
//        strategy.setEntityTableFieldAnnotationEnable(true); //设置实体类TableField映射
//        strategy.setEntityBuilderModel(true);
//        strategy.setCapitalMode(true);
//        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
//        strategy.setEntityColumnConstant(true);
//        strategy.setSkipView(true);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.study.generator");
        pc.setController("controller");
//        pc.setService("serviceI");
        pc.setServiceImpl("service");
        pc.setMapper("mapper");
        pc.setEntity("model");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }

}