package com.tools.common.wxtool.qywechat.apis;

/**
 * @ClassName：Oauth2API
 * @Description：TODO
 * @Author LinLuoChen
 * @Date 2020/8/13/15:55
 * @Version V1.0
 **/
public class Oauth2API {

    // 获取 oauth2 API
    private final static String qy_oauth2_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";

    // 获取 oauth2 API
    public static String getOauth2Url(String CORPID){
        return qy_oauth2_url.replaceAll("CORPID", CORPID).replace("REDIRECT_URI","回调地址（项目的地址）").replace("SCOPE","snsapi_base");
    }

}
