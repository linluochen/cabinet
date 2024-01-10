package com.tools.common.wxtool.qywechat.pojo;

import lombok.Data;

/**
 * @Description Access_Token 凭证
 * @Author LinLuoChen
 * @Date  14:55
 **/
@Data
public class AccessToken {
	
    // 获取到的凭证  
    private String token;  
	
    // 凭证有效时间，单位：秒  
    private int expiresIn;  

}
