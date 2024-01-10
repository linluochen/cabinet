package com.tools.common.wxtool.qywechat.apis;

import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.qywechat.enums.EnumMethod;
import com.tools.common.wxtool.qywechat.pojo.UserID;
import com.tools.common.wxtool.qywechat.utils.HttpRequestUtil;

/**
 * @ClassName：UserIDAPI
 * @Description：TODO 获取访问用户身份
 * @Author LinLuoChen
 * @Date 2020/8/13/15:17
 * @Version V1.0
 **/
public class UserIdAPI {

    // 企业微信获取访问用户身份  
    private final static String qy_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";

    //获取访问用户身份
    public static UserID getUserIdInfo(String ACCESSTOKEN, String CODE){
        UserID userID = new UserID();
        String requestURL = qy_user_url.replace("ACCESSTOKEN", ACCESSTOKEN).replace( "CODE", CODE);
        JSONObject jsonObject = HttpRequestUtil.httpRequest(requestURL, EnumMethod.GET.name(), null);
        // 如果请求成功
        if (jsonObject != null) {
            try {
                System.out.println("获取访问用户身份接口返回信息："+jsonObject);
                userID.setUserId(jsonObject.getString("UserId"));
                userID.setDeviceId(jsonObject.getString("DeviceId"));
            } catch (Exception e) {
                userID = null;
            }
        }
        return userID;
    }

}
