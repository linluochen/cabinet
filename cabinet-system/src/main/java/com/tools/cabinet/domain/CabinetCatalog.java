package com.tools.cabinet.domain;

import com.tools.common.annotation.Excel;
import com.tools.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 主页目录对象 cabinet_catalog
 *
 * @author tools
 * @date 2023-04-03
 */
@Data
public class CabinetCatalog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String url;

    /**
     * 类型ID
     */
    @Excel(name = "类型ID")
    private Integer typeId;

    /**
     * 删除标识（0 正在使用 1 已删除）
     */
    @Excel(name = "删除标识", readConverterExp = "0=使用中, 1=已删除")
    private Integer del;

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

    // 部门名称
    private String typeName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("url", getUrl())
                .append("typeId", getTypeId())
                .append("del", getDel())
                .append("createTime", getCreateTime())
                .append("createName", getCreateName())
                .append("updateTime", getUpdateTime())
                .append("updateName", getUpdateName())
                .toString();
    }
}
