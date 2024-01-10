package com.tools.cabinet.service;

import com.tools.cabinet.domain.CabinetCatalog;

import java.util.List;

/**
 * 主页目录Service接口
 *
 * @author tools
 * @date 2023-04-03
 */
public interface ICabinetCatalogService {
    /**
     * 查询主页目录
     *
     * @param id 主页目录主键
     * @return 主页目录
     */
    public CabinetCatalog selectCabinetCatalogById(Long id);

    /**
     * 查询主页目录列表
     *
     * @param cabinetCatalog 主页目录
     * @return 主页目录集合
     */
    public List<CabinetCatalog> selectCabinetCatalogList(CabinetCatalog cabinetCatalog);

    /**
     * 新增主页目录
     *
     * @param cabinetCatalog 主页目录
     * @return 结果
     */
    public int insertCabinetCatalog(CabinetCatalog cabinetCatalog);

    /**
     * 修改主页目录
     *
     * @param cabinetCatalog 主页目录
     * @return 结果
     */
    public int updateCabinetCatalog(CabinetCatalog cabinetCatalog);

    /**
     * 批量删除主页目录
     *
     * @param ids 需要删除的主页目录主键集合
     * @return 结果
     */
    public int deleteCabinetCatalogByIds(Long[] ids);

    /**
     * 删除主页目录信息
     *
     * @param id 主页目录主键
     * @return 结果
     */
    public int deleteCabinetCatalogById(Long id);
}
