package com.tools.common.wxtool.wechat.apis;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.wechat.enums.EnumMethod;
import com.tools.common.wxtool.wechat.pojo.IPWhite;
import com.tools.common.wxtool.wechat.utils.HttpRequestUtil;

/**
 * @Description 公众平台通用接口工具类
 * @Author LinLuoChen
 * @Date 2023/7/25 16:14
 **/
public class IPWhiteListAPI {

    // 获取微信公众号：ip的接口地址（GET）无请求限制，需要定时刷新，建议每天定时刷新
    public final static String ip_url = "https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=ACCESS_TOKEN";

    /**
     * @Description 获取 IP 白名单
     * @Author LinLuoChen
     * @Date 2023/7/25 16:24
     * @Param [accessToken（凭证）]
     * @Return com.tools.common.wxtool.wechat.pojo.IPWhite
     **/
    public static IPWhite getIPWhiteList(String accessToken) {
        IPWhite ipWhite = null;
        String requestUrl = ip_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
        if (json == null) {
            json = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
        }
        // 如果请求成功
        if (null != json) {
            try {
                ipWhite = new IPWhite();
                ipWhite.setIPList(json.getJSONArray("ip_list"));
            } catch (JSONException e) {
                // 获取token失败
                ipWhite = null;
            }
        }
        return ipWhite;
    }

}
