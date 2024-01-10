package com.tools.cabinet.mapper;

import com.tools.cabinet.domain.CabinetType;

import java.util.List;

/**
 * 类型Mapper接口
 *
 * @author tools
 * @date 2023-04-03
 */
public interface CabinetTypeMapper {
    /**
     * 查询类型
     *
     * @param id 类型主键
     * @return 类型
     */
    public CabinetType selectCabinetTypeById(Long id);

    /**
     * 查询类型列表
     *
     * @param cabinetType 类型
     * @return 类型集合
     */
    public List<CabinetType> selectCabinetTypeList(CabinetType cabinetType);

    /**
     * 新增类型
     *
     * @param cabinetType 类型
     * @return 结果
     */
    public int insertCabinetType(CabinetType cabinetType);

    /**
     * 修改类型
     *
     * @param cabinetType 类型
     * @return 结果
     */
    public int updateCabinetType(CabinetType cabinetType);

    /**
     * 删除类型
     *
     * @param id 类型主键
     * @return 结果
     */
    public int deleteCabinetTypeById(Long id);

    /**
     * 批量删除类型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCabinetTypeByIds(Long[] ids);
}
