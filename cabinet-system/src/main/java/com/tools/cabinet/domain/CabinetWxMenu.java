package com.tools.cabinet.domain;

import com.tools.common.annotation.Excel;
import com.tools.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 微信菜单对象 cabinet_wx_menu
 *
 * @author tools
 * @date 2023-04-23
 */
@Data
public class CabinetWxMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * Pid
     */
    private Long parentId;

    /**
     * 标题名称
     */
    @Excel(name = "标题名称")
    private String title;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String describes;

    /**
     * 描述
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 状态
     **/
    @Excel(name = "状态(0 启用 1 停用)")
    private Integer status;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("title", getTitle())
                .append("describe", getDescribes())
                .append("sort", getSort())
                .append("remark", getRemark())
                .append("status", getStatus())
                .toString();
    }
}
