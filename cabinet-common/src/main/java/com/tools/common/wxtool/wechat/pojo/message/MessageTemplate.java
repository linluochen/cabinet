package com.tools.common.wxtool.wechat.pojo.message;

import lombok.Data;

/**
 * @Title: MessageTemplate
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description:
 * @Date 2023/7/31 16:33
 */
@Data
public class MessageTemplate {

    // 标题
    private String first;

    // 内容1
    private String keyword1;

    // 内容2
    private String keyword2;

    // 内容3
    private String keyword3;

    // 内容4
    private String keyword4;

    // 结束
    private String remark;

}
