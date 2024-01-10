package com.tools.cabinet.service;

import com.tools.cabinet.domain.CabinetWxConf;

import java.util.List;

/**
 * 微信配置Service接口
 *
 * @author tools
 * @date 2023-04-21
 */
public interface ICabinetWxConfService {
    /**
     * 查询微信配置
     *
     * @param id 微信配置主键
     * @return 微信配置
     */
    public CabinetWxConf selectCabinetWxConfById(Long id);

    /**
     * 查询微信配置列表
     *
     * @param cabinetWxConf 微信配置
     * @return 微信配置集合
     */
    public List<CabinetWxConf> selectCabinetWxConfList(CabinetWxConf cabinetWxConf);

    /**
     * 新增微信配置
     *
     * @param cabinetWxConf 微信配置
     * @return 结果
     */
    public int insertCabinetWxConf(CabinetWxConf cabinetWxConf);

    /**
     * 修改微信配置
     *
     * @param cabinetWxConf 微信配置
     * @return 结果
     */
    public int updateCabinetWxConf(CabinetWxConf cabinetWxConf);

    /**
     * 批量删除微信配置
     *
     * @param ids 需要删除的微信配置主键集合
     * @return 结果
     */
    public int deleteCabinetWxConfByIds(Long[] ids);

    /**
     * 删除微信配置信息
     *
     * @param id 微信配置主键
     * @return 结果
     */
    public int deleteCabinetWxConfById(Long id);
}
