package com.tools.common.wxtool.wechat.apis;


import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.wechat.utils.HttpRequestUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: OAuthLogAPI
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 网页授权登录工具类
 * @Date 2023/9/6 9:41
 */
public class OAuthLogAPI {

    /**
     * 获取授权 code 参数，参数说明
     * appid 公众号的 id -- 需要替换的参数
     * redirect_uri 请求成功后执行回调的地址 -- 需要替换的参数
     * scope 默认 snsapi_base 不弹出授权页面（不关注拿不到用户信息）
     * snsapi_userinfo 弹出授权页面（即使在未关注的情况下也可以拿到授权信息）
     * 注意：跳转回调 redirect_uri 应当使用 https 链接来确保授权 code 的安全性
     **/
    private String oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=RedirectURL&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

    /**
     * 获取 token 和 openid 参数
     * appid 公众号的 id -- 需要替换的参数
     * secret 公众号的密钥 - 需要替换的参数
     * code 微信回调自带 code（code 只能使用一次 5 分钟未被使用自动过期）
     **/
    private String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    // 第一步 通过网页授权链接获取 code 信息
    public String getOAuthLog(String appID, HttpServletRequest request) throws Exception {
        // 获取当前域名，用于拼装回调参数
        String basePath = request.getScheme() + "://" + request.getServerName() + "/";
        // 替换参数信息
        oauth_url = oauth_url.replace("APPID", appID).replace("RedirectURL", basePath);
        return oauth_url;
    }

    // 第二步 通过 code 换取 token 和 openid
    public JSONObject getCodeAllToken(String code, String appid, String secret) {
        token_url = token_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        return HttpRequestUtil.httpRequest(token_url, "POST", "");
    }


}
