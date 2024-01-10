package com.tools.cabinet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tools.common.annotation.Excel;
import com.tools.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 微信配置对象 cabinet_wx_conf
 *
 * @author tools
 * @date 2023-04-21
 */
@Data
public class CabinetWxConf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 微信菜单 ID
     */
    @Excel(name = "微信菜单ID")
    private String wxMenuId;

    /**
     * 微信凭证ID
     */
    @Excel(name = "微信标题")
    private String wxTitle;

    /**
     * 微信凭证ID
     */
    @Excel(name = "微信凭证ID")
    private String appid;

    /**
     * 微信凭证密钥
     */
    @Excel(name = "微信凭证密钥")
    private String secret;

    /**
     * token 凭证
     */
    @Excel(name = "token 凭证")
    private String accessToken;

    /**
     * 凭证有效时间
     */
    @Excel(name = "凭证有效时间")
    private Integer expiresIn;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 类型 1 微信 2 企业微信
     */
    @Excel(name = "类型 1 微信 2 企业微信 ")
    private Integer type;

    // 查询列表 -- 微信菜单名称
    private String title;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("wxMenuId", getWxMenuId())
                .append("wxTitle", getWxTitle())
                .append("appid", getAppid())
                .append("secret", getSecret())
                .append("accessToken", getAccessToken())
                .append("expiresIn", getExpiresIn())
                .append("createTime", getCreateTime())
                .append("type", getType())
                .toString();
    }
}
