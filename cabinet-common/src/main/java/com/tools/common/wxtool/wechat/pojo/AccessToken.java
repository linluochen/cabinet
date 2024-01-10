package com.tools.common.wxtool.wechat.pojo;

import lombok.Data;

/**
 * @Description Pojo 实体类
 * @Author LinLuoChen
 * @Date 2023/7/25 16:22
 **/
@Data
public class AccessToken {
    // 获取到的凭证  
    private String token;

    // 凭证有效时间，单位：秒  
    private int expiresIn;
}
