package com.tools.common.wxtool.wechat.message.corp;


import com.tools.common.wxtool.wechat.enums.EnumMessageType;
import lombok.Data;

/**
 * 企业文本消息
 *
 * @author Sunlight
 */
@Data
public class QiYeTextMsg extends QiYeBaseMsg {

    private QiYeText text;

    public QiYeTextMsg() {
        this.msgtype = EnumMessageType.text.name();
    }

}
