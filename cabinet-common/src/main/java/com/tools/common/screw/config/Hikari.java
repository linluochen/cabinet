package com.tools.common.screw.config;

import com.zaxxer.hikari.HikariConfig;

/**
 * @Title: HikariConfig
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 配置数据源类
 * @Date 2023/7/24 11:11
 */
public class Hikari {

    public static HikariConfig getHikari() {
        //数据源
        HikariConfig hikariConfig = new com.zaxxer.hikari.HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/tool_cabinet");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        //可以获取 tables 表注释信息 即解决数据库表和列字段有说明、生成文档没有说明
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return hikariConfig;
    }

}
