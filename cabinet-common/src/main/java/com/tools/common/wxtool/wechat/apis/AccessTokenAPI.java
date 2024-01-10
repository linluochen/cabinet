package com.tools.common.wxtool.wechat.apis;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.wechat.enums.EnumMethod;
import com.tools.common.wxtool.wechat.pojo.AccessToken;
import com.tools.common.wxtool.wechat.utils.HttpRequestUtil;

/**
 * @Description 公众平台通用接口工具类
 * @Author LinLuoChen
 * @Date 2023/7/24 10:38
 **/
public class AccessTokenAPI {

    // 获取微信公众号：access_token的接口地址（GET） 限 2000（次/天），单次有效期 2（H/次），需要定时刷新
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * @Description 获取 access_token
     * @Author LinLuoChen
     * @Date 2023/7/24 10:38
     * @Param [appid, appsecret]
     * @Return com.tools.common.wxtool.wechat.pojo.AccessToken
     **/
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject json = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
        if (json == null) {
            json = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
        }
        System.out.println(json);
        // 如果请求成功
        if (null != json) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(json.getString("access_token"));
                accessToken.setExpiresIn(json.getInteger("expires_in"));
            } catch (JSONException e) {
                // 获取token失败
                accessToken = null;
            }
        }
        return accessToken;
    }

    public static void main(String[] args) {
        System.out.println(getAccessToken("wxac126ab9af8ba223", "ec621303cd0ae68db1b916b6b281a8c9"));
    }


}
