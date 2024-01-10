package com.tools.common.wxtool.qywechat.utils;

import com.alibaba.fastjson.JSONObject;
import com.tools.common.utils.html.EscapeUtil;
import com.tools.common.wxtool.qywechat.apis.AccessTokenAPI;
import com.tools.common.wxtool.qywechat.enums.EnumMethod;
import com.tools.common.wxtool.qywechat.pojo.AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @ClassName：WxConfig
 * @Description：TODO
 * @Author LinLuoChen
 * @Date 2021/5/9/0:27
 * @Version V1.0
 **/
@Service
public class WxConfigUtils {

    private String CorpID = "";

    private String Secret = "";

    public Map<String,Object> getWxconfig(HttpServletRequest request){
        System.out.println("进入 WxConfig 方法！！！");
        String params = request.getQueryString();
        if(StringUtils.isEmpty(params)){
            params="";
        }else{
            params="?"+params;
        }
        String url = "https://hb.sdpicc.com.cn"+request.getRequestURI()+params;
//        String url = "http://shandong.ruiyichen.top"+request.getRequestURI()+params;
        //request.getRequestURL().toString();
        Map<String,Object> map = new HashMap<String,Object>();
        // 时间戳
        String timestamp = Long.toString((new Date().getTime()) / 1000);
        // 随机串
        String nonceStr = UUID.randomUUID().toString();
        // 获取 access_token
        AccessToken accessToken = AccessTokenAPI.getAccessToken(CorpID,Secret);
        // 根据 tocker 获取 jstl
        String urlStr = "http://46.0.24.56:7010/cgibin/get_jsapi_ticket?access_token="+accessToken.getToken();
//        String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="+accessToken.getToken();
        JSONObject jsonObject = HttpRequestUtil.httpRequest(urlStr, EnumMethod.GET.name(), null);
        String ticket = jsonObject.getString("ticket");
        System.out.println("要加密的参数："+nonceStr+" "+ticket+" "+timestamp+" "+url);
        String signature = getsig(nonceStr,ticket,timestamp,url);
        map.put("appId",CorpID);
        map.put("timestamp",timestamp);
        map.put("nonceStr",nonceStr);
        map.put("signature",signature);
        System.out.println("返回结果Map:"+map);
        return EscapeUtil.cleanMap(map);
    }



    private static String getsig(String noncestr,String jsapi_ticket,String timestamp,String url){
        String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket,
                "timestamp=" + timestamp, "noncestr=" + noncestr, "url=" + url };
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
                .concat("&"+paramArr[3]);
        String gensignature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            System.out.println("拼接加密签名："+content);
            byte[] digest = md.digest(content.getBytes());
            gensignature = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将 sha1 加密后的字符串与 signature 进行对比
        if (gensignature != null) {
            return gensignature;// 返回signature
        } else {
            return "false";
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

}
