package com.tools.cabinet.domain;

import com.tools.common.annotation.Excel;
import com.tools.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 类型对象 cabinet_type
 *
 * @author tools
 * @date 2023-04-03
 */
@Data
public class CabinetType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 上级ID
     */
    @Excel(name = "上级ID")
    private Long parentId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 创建人员
     */
    @Excel(name = "创建人员")
    private String createName;

    /**
     * 修改人员
     */
    @Excel(name = "修改人员")
    private String updateName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("name", getName())
                .append("createTime", getCreateTime())
                .append("createName", getCreateName())
                .append("updateTime", getUpdateTime())
                .append("updateName", getUpdateName())
                .toString();
    }
}
