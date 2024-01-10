package com.tools.common.wxtool.qywechat.apis;


import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.qywechat.enums.EnumMethod;
import com.tools.common.wxtool.qywechat.pojo.AccessToken;
import com.tools.common.wxtool.qywechat.utils.HttpRequestUtil;

/**
 * 公众平台通用接口工具类 (目前仅包含企业微信)
 */
public class AccessTokenAPI {

    // 获取企业微信 access_token    
    private final static String qy_access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";

    // 获取凭证信息
    public static AccessToken getAccessToken(String CORPID, String CORPSECRET){
        AccessToken accessToken = new AccessToken();
        String requestURL = qy_access_token_url.replace("CORPID", CORPID).replace( "CORPSECRET", CORPSECRET);
        JSONObject jsonObject = HttpRequestUtil.httpRequest(requestURL, EnumMethod.GET.name(), null);
        // 如果请求成功
        if (jsonObject != null) {
            try {
                System.out.println("获取获取凭证信息："+jsonObject);
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
            } catch (Exception e) {
                accessToken = null;
            }
        }
        return accessToken;
    }

}