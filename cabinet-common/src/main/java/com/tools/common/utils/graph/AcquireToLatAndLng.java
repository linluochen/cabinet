package com.tools.common.utils.graph;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.tools.common.utils.StringUtils;
import com.tools.common.utils.http.HttpUtils;

/**
 * @Title: AcquireToLatAndLng
 * @Author LinLuoChen
 * @ProjectName RuoYi-Vue-master
 * @Description: 根据百度地图检索获取经纬度信息
 * @Date 2023/6/1 14:55
 */
public class AcquireToLatAndLng {

    private static final String url = "https://api.map.baidu.com/place/v2/search?query=CompanyName&region=%E5%AE%81%E9%98%B3%E5%8E%BF&output=json&ak=7L2zzpWAhH0F8sE7FO3N4FsitCCWH6Cp";

    /**
     * @Description 调用百度地图执行纠正经纬度
     * @Author LinLuoChen
     * @Date 2023/6/1 15:16
     **/
    public static JSONObject getComNameToLat(String companyName) {
        String strUrl = url.replace("CompanyName", companyName);
        String result = HttpUtils.sendGet(strUrl);
        if (StringUtils.isNotEmpty(result)) {
            if (JSONObject.parseObject(result.trim()).getString("message").equals("ok")) {
                JSONArray array = JSONArray.parse(JSONObject.parseObject(result.trim()).getString("results"));
                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    // 通过企业全称查询
                    if (obj.getString("name").contains(companyName)) {
                        return JSONObject.parseObject(obj.getString("location"));
                    }
                    // 通过企业关键字查询
                    if (companyName.equals(obj.getString("name").replace("宁阳", "").replace("有限公司", "").replace("泰安", "").replace("山东", ""))) {
                        return JSONObject.parseObject(obj.getString("location"));
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getComNameToLat("宁阳县文庙中心幼儿园"));
    }

}
