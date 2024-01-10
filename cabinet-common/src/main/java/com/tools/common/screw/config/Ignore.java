package com.tools.common.screw.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: Ignore
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 配置忽略类
 * @Date 2023/7/24 14:07
 */
public class Ignore {

    public static Map<String, ArrayList<String>> getIgnore() {
        Map<String, ArrayList<String>> map = new HashMap<>();
        // 忽略表名
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        map.put("name", ignoreTableName);
        //忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        map.put("prefix", ignoreTableName);
        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");
        map.put("suffix", ignoreTableName);
        return map;
    }

}
