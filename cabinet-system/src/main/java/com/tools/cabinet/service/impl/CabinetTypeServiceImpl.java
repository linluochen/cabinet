package com.tools.cabinet.service.impl;

import com.tools.cabinet.domain.CabinetType;
import com.tools.cabinet.mapper.CabinetTypeMapper;
import com.tools.cabinet.service.ICabinetTypeService;
import com.tools.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类型Service业务层处理
 *
 * @author tools
 * @date 2023-04-03
 */
@Service
public class CabinetTypeServiceImpl implements ICabinetTypeService {
    @Autowired
    private CabinetTypeMapper cabinetTypeMapper;

    /**
     * 查询类型
     *
     * @param id 类型主键
     * @return 类型
     */
    @Override
    public CabinetType selectCabinetTypeById(Long id) {
        return cabinetTypeMapper.selectCabinetTypeById(id);
    }

    /**
     * 查询类型列表
     *
     * @param cabinetType 类型
     * @return 类型
     */
    @Override
    public List<CabinetType> selectCabinetTypeList(CabinetType cabinetType) {
        return cabinetTypeMapper.selectCabinetTypeList(cabinetType);
    }

    /**
     * 新增类型
     *
     * @param cabinetType 类型
     * @return 结果
     */
    @Override
    public int insertCabinetType(CabinetType cabinetType) {
        cabinetType.setCreateTime(DateUtils.getNowDate());
        return cabinetTypeMapper.insertCabinetType(cabinetType);
    }

    /**
     * 修改类型
     *
     * @param cabinetType 类型
     * @return 结果
     */
    @Override
    public int updateCabinetType(CabinetType cabinetType) {
        cabinetType.setUpdateTime(DateUtils.getNowDate());
        return cabinetTypeMapper.updateCabinetType(cabinetType);
    }

    /**
     * 批量删除类型
     *
     * @param ids 需要删除的类型主键
     * @return 结果
     */
    @Override
    public int deleteCabinetTypeByIds(Long[] ids) {
        return cabinetTypeMapper.deleteCabinetTypeByIds(ids);
    }

    /**
     * 删除类型信息
     *
     * @param id 类型主键
     * @return 结果
     */
    @Override
    public int deleteCabinetTypeById(Long id) {
        return cabinetTypeMapper.deleteCabinetTypeById(id);
    }
}
