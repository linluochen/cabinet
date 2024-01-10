package com.tools.common.wxtool.wechat.apis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.wechat.pojo.AccessToken;
import com.tools.common.wxtool.wechat.pojo.message.MessageTemplate;
import com.tools.common.wxtool.wechat.pojo.message.TemplateData;
import com.tools.common.wxtool.wechat.pojo.message.WechatTemplate;
import com.tools.common.wxtool.wechat.utils.HttpRequestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 公众平台通用接口工具类
 * @Author LinLuoChen
 * @Date 2023/7/24 10:38
 */
public class TemplateMessSendAPI {

    // 获取微信公众号：推送模板消息（POST） 限 10w（次/天）
    public final static String message_send_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    // 发起救援
    public static void getSendTemplateMessage(String openId, MessageTemplate messageTemplate, String accessToken) {
        // 配置推送连接
        String requestUrl = message_send_url.replace("ACCESS_TOKEN", accessToken);
        WechatTemplate wechatTemplate = new WechatTemplate();
        //设置模板id
        wechatTemplate.setTemplate_id("lfyAbbeNsD3CQbNvI_X_3PPgW7pKiZDj1SFSO7EQeKY");  //需要替换成自己的
        wechatTemplate.setTouser(openId);
        wechatTemplate.setTopcolor("#000000");
        //模板消息点击跳转路径, 如果不配置跳转小程序,可以设置跳转该路径
        //wechatTemplate.setUrl("http://www.baidu.com");

        // 配置模版消息
        Map<String, TemplateData> m = new HashMap<String, TemplateData>();
        TemplateData first = new TemplateData();
        first.setColor("#000000");
        first.setValue(messageTemplate.getFirst());
        m.put("first", first);

        TemplateData keyword1 = new TemplateData();
        keyword1.setColor("#000000");
        keyword1.setValue(messageTemplate.getKeyword1());
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setColor("#000000");
        keyword2.setValue(messageTemplate.getKeyword2());
        m.put("keyword2", keyword2);

        TemplateData keyword3 = new TemplateData();
        keyword3.setColor("#000000");
        keyword3.setValue(messageTemplate.getKeyword3());
        m.put("keyword3", keyword3);

        TemplateData keyword4 = new TemplateData();
        keyword4.setColor("#000000");
        keyword4.setValue(messageTemplate.getKeyword4());
        m.put("keyword4", keyword4);

        TemplateData remark = new TemplateData();
        remark.setColor("#000000");
        remark.setValue(messageTemplate.getRemark());
        m.put("remark", remark);
        wechatTemplate.setData(m);

        JSONObject jsonObject = HttpRequestUtil.httpRequest(requestUrl, "POST", JSON.toJSONString(wechatTemplate));
        System.out.println("消息模版返回结果：" + jsonObject.toString());
    }

    public static void main(String[] args) {
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setFirst("尊敬的用户");
        messageTemplate.setKeyword1("济南场馆");
        messageTemplate.setKeyword2("12000");
        messageTemplate.setKeyword3(new Date().toString());
        messageTemplate.setKeyword4("10000");
        messageTemplate.setRemark("感谢您的使用");
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wxac126ab9af8ba223", "ec621303cd0ae68db1b916b6b281a8c9");
        getSendTemplateMessage("oRSFJ6-YaFU1B238CXfs_tqyYxMM", messageTemplate, accessToken.getToken());
    }

}
