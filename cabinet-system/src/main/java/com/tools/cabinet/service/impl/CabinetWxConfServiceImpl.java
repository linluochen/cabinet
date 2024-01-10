package com.tools.cabinet.service.impl;

import com.tools.cabinet.domain.CabinetWxConf;
import com.tools.cabinet.mapper.CabinetWxConfMapper;
import com.tools.cabinet.service.ICabinetWxConfService;
import com.tools.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信配置Service业务层处理
 *
 * @author tools
 * @date 2023-04-21
 */
@Service
public class CabinetWxConfServiceImpl implements ICabinetWxConfService {
    @Autowired
    private CabinetWxConfMapper cabinetWxConfMapper;

    /**
     * 查询微信配置
     *
     * @param id 微信配置主键
     * @return 微信配置
     */
    @Override
    public CabinetWxConf selectCabinetWxConfById(Long id) {
        return cabinetWxConfMapper.selectCabinetWxConfById(id);
    }

    /**
     * 查询微信配置列表
     *
     * @param cabinetWxConf 微信配置
     * @return 微信配置
     */
    @Override
    public List<CabinetWxConf> selectCabinetWxConfList(CabinetWxConf cabinetWxConf) {
        return cabinetWxConfMapper.selectCabinetWxConfList(cabinetWxConf);
    }

    /**
     * 新增微信配置
     *
     * @param cabinetWxConf 微信配置
     * @return 结果
     */
    @Override
    public int insertCabinetWxConf(CabinetWxConf cabinetWxConf) {
        return cabinetWxConfMapper.insertCabinetWxConf(cabinetWxConf);
    }

    /**
     * 修改微信配置
     *
     * @param cabinetWxConf 微信配置
     * @return 结果
     */
    @Override
    public int updateCabinetWxConf(CabinetWxConf cabinetWxConf) {
        cabinetWxConf.setCreateTime(DateUtils.NowDateTimex2());
        return cabinetWxConfMapper.updateCabinetWxConf(cabinetWxConf);
    }

    /**
     * 批量删除微信配置
     *
     * @param ids 需要删除的微信配置主键
     * @return 结果
     */
    @Override
    public int deleteCabinetWxConfByIds(Long[] ids) {
        return cabinetWxConfMapper.deleteCabinetWxConfByIds(ids);
    }

    /**
     * 删除微信配置信息
     *
     * @param id 微信配置主键
     * @return 结果
     */
    @Override
    public int deleteCabinetWxConfById(Long id) {
        return cabinetWxConfMapper.deleteCabinetWxConfById(id);
    }
}
