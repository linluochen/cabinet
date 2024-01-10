package com.tools.common.wxtool.qywechat.pojo;

import lombok.Data;

/**
 * @ClassName：UserId
 * @Description：TODO 用户身份
 * @Author LinLuoChen
 * @Date 2020/8/13/15:18
 * @Version V1.0
 **/
@Data
public class UserID {

    // 成员UserID
    private String UserId;
	
    // 手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)
    private String DeviceId;

}
