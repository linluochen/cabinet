package com.tools.common.screw;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.tools.common.screw.config.Hikari;
import com.tools.common.screw.config.Ignore;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * @Title: documentGeneration
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 生成数据库文档
 * @Date 2023/7/24 11:08
 */
public class SQLDocGeneration {

    private static final String FILE_OUTPUT_DIR = "F:\\dbDoc\\";

    /**
     * @Description 数据库文档生成
     * @Author LinLuoChen
     * @Date 2023/7/24 11:11
     * @Return void
     **/
    private static void documentGeneration(String fileName) {
        //数据源
        DataSource dataSource = new HikariDataSource(Hikari.getHikari());
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(FILE_OUTPUT_DIR)
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.HTML)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName(fileName).build();
        ProcessConfig processConfig = ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(Ignore.getIgnore().get("name"))
                //忽略表前缀
                .ignoreTablePrefix(Ignore.getIgnore().get("prefix"))
                //忽略表后缀
                .ignoreTableSuffix(Ignore.getIgnore().get("suffix")).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("数据库设计文档生成")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig).build();
        //执行生成
        new DocumentationExecute(config).execute();
    }

    // 测试方法
    public static void main(String[] args) {
        SQLDocGeneration.documentGeneration("测试生成文档");
    }

}
