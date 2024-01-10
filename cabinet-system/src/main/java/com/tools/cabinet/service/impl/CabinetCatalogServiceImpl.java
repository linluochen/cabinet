package com.tools.cabinet.service.impl;

import com.tools.cabinet.domain.CabinetCatalog;
import com.tools.cabinet.mapper.CabinetCatalogMapper;
import com.tools.cabinet.service.ICabinetCatalogService;
import com.tools.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主页目录Service业务层处理
 *
 * @author tools
 * @date 2023-04-03
 */
@Service
public class CabinetCatalogServiceImpl implements ICabinetCatalogService {
    @Autowired
    private CabinetCatalogMapper cabinetCatalogMapper;

    /**
     * 查询主页目录
     *
     * @param id 主页目录主键
     * @return 主页目录
     */
    @Override
    public CabinetCatalog selectCabinetCatalogById(Long id) {
        return cabinetCatalogMapper.selectCabinetCatalogById(id);
    }

    /**
     * 查询主页目录列表
     *
     * @param cabinetCatalog 主页目录
     * @return 主页目录
     */
    @Override
    public List<CabinetCatalog> selectCabinetCatalogList(CabinetCatalog cabinetCatalog) {
        return cabinetCatalogMapper.selectCabinetCatalogList(cabinetCatalog);
    }

    /**
     * 新增主页目录
     *
     * @param cabinetCatalog 主页目录
     * @return 结果
     */
    @Override
    public int insertCabinetCatalog(CabinetCatalog cabinetCatalog) {
        cabinetCatalog.setCreateTime(DateUtils.getNowDate());
        return cabinetCatalogMapper.insertCabinetCatalog(cabinetCatalog);
    }

    /**
     * 修改主页目录
     *
     * @param cabinetCatalog 主页目录
     * @return 结果
     */
    @Override
    public int updateCabinetCatalog(CabinetCatalog cabinetCatalog) {
        cabinetCatalog.setUpdateTime(DateUtils.getNowDate());
        return cabinetCatalogMapper.updateCabinetCatalog(cabinetCatalog);
    }

    /**
     * 批量删除主页目录
     *
     * @param ids 需要删除的主页目录主键
     * @return 结果
     */
    @Override
    public int deleteCabinetCatalogByIds(Long[] ids) {
        return cabinetCatalogMapper.deleteCabinetCatalogByIds(ids);
    }

    /**
     * 删除主页目录信息
     *
     * @param id 主页目录主键
     * @return 结果
     */
    @Override
    public int deleteCabinetCatalogById(Long id) {
        return cabinetCatalogMapper.deleteCabinetCatalogById(id);
    }
}
