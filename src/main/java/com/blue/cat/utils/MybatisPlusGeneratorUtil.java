package com.blue.cat.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author melo
 * @date 2020/5/31
 */
public class MybatisPlusGeneratorUtil {
    public static void main(String[] args) {
        String author = "lixin";
        String database = "bluecat";
        String tableName = "discern_record";
        generateByTables(author, database,tableName);
    }

    private static void generateByTables(String author, String database, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir(System.getProperty("user.dir") +"/src/main/java/")
                .setFileOverride(true)
                .setEnableCache(false)
                .setEntityName("%s")
                .setMapperName("%sMapper")
               // .setServiceName("%sService")
                //.setServiceImplName("%sServiceImpl")
                .setOpen(false);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
//                .setPassword("root")
                .setDriverName("com.mysql.cj.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
//				.setSuperMapperClass("")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent("com.blue.cat")
                                .setEntity("bean.entity.DiscernRecord")
                                .setMapper("mapper")
                                //.setService("service")
                                //.setServiceImpl("service.impl")
                                //.setXml("mybatis.mappers")
                ).execute();
    }
}
