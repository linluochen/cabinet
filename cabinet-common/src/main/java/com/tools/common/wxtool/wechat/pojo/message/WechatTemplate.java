package com.tools.common.wxtool.wechat.pojo.message;

import lombok.Data;

import java.util.Map;

/**
 * @Title: WechatTemplate
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description:
 * @Date 2023/7/31 16:30
 */
@Data
public class WechatTemplate {

    // 标题颜色
    public String topcolor;
    // 接收人
    private String touser;
    // 模板消息 ID
    private String template_id;
    // 跳转路径
    private String url;
    // 模板内容信息
    private Map<String, TemplateData> data;

}
