package com.tools.common.utils.graph;

import lombok.Data;

/**
 * @Title: JqPoint
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description:
 * @Date 2023/5/31 11:20
 */
@Data
/**
 * 经纬度VO
 *
 * @author Klay
 * @date 2023/2/8
 */
public class JqPoint {

    private Double x;

    private Double y;

    public JqPoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}


