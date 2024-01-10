package com.tools.cabinet.service.impl;

import com.tools.cabinet.domain.CabinetWxMenu;
import com.tools.cabinet.mapper.CabinetWxMenuMapper;
import com.tools.cabinet.service.ICabinetWxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 微信菜单Service业务层处理
 *
 * @author tools
 * @date 2023-04-23
 */
@Service
public class CabinetWxMenuServiceImpl implements ICabinetWxMenuService {
    @Autowired
    private CabinetWxMenuMapper cabinetWxMenuMapper;

    /**
     * 查询微信菜单
     *
     * @param id 微信菜单主键
     * @return 微信菜单
     */
    @Override
    public CabinetWxMenu selectCabinetWxMenuById(Long id) {
        return cabinetWxMenuMapper.selectCabinetWxMenuById(id);
    }

    /**
     * 查询微信菜单列表
     *
     * @param cabinetWxMenu 微信菜单
     * @return 微信菜单
     */
    @Override
    public List<CabinetWxMenu> selectCabinetWxMenuList(CabinetWxMenu cabinetWxMenu) {
        return cabinetWxMenuMapper.selectCabinetWxMenuList(cabinetWxMenu);
    }

    /**
     * 新增微信菜单
     *
     * @param cabinetWxMenu 微信菜单
     * @return 结果
     */
    @Override
    public int insertCabinetWxMenu(CabinetWxMenu cabinetWxMenu) {
        return cabinetWxMenuMapper.insertCabinetWxMenu(cabinetWxMenu);
    }

    /**
     * 修改微信菜单
     *
     * @param cabinetWxMenu 微信菜单
     * @return 结果
     */
    @Override
    public int updateCabinetWxMenu(CabinetWxMenu cabinetWxMenu) {
        return cabinetWxMenuMapper.updateCabinetWxMenu(cabinetWxMenu);
    }

    /**
     * 批量删除微信菜单
     *
     * @param ids 需要删除的微信菜单主键
     * @return 结果
     */
    @Override
    public int deleteCabinetWxMenuByIds(Long[] ids) {
        return cabinetWxMenuMapper.deleteCabinetWxMenuByIds(ids);
    }

    /**
     * 删除微信菜单信息
     *
     * @param id 微信菜单主键
     * @return 结果
     */
    @Override
    public int deleteCabinetWxMenuById(Long id) {
        return cabinetWxMenuMapper.deleteCabinetWxMenuById(id);
    }

    /**
     * @Description 查询微信菜单列表所有父级列表
     * @Author LinLuoChen
     * @Date 2023/8/9 14:47
     * @Return java.util.List<Map < Object, Object>>
     **/
    @Override
    public List<Map<String, Object>> selectCabinetWxParentMenuList() {
        return cabinetWxMenuMapper.selectCabinetWxParentMenuList();
    }
}
