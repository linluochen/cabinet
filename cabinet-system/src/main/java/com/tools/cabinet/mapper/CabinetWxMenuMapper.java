package com.tools.cabinet.mapper;

import com.tools.cabinet.domain.CabinetWxMenu;

import java.util.List;
import java.util.Map;

/**
 * 微信菜单Mapper接口
 *
 * @author tools
 * @date 2023-04-23
 */
public interface CabinetWxMenuMapper {
    /**
     * 查询微信菜单
     *
     * @param id 微信菜单主键
     * @return 微信菜单
     */
    public CabinetWxMenu selectCabinetWxMenuById(Long id);

    /**
     * 查询微信菜单列表
     *
     * @param cabinetWxMenu 微信菜单
     * @return 微信菜单集合
     */
    public List<CabinetWxMenu> selectCabinetWxMenuList(CabinetWxMenu cabinetWxMenu);

    /**
     * 新增微信菜单
     *
     * @param cabinetWxMenu 微信菜单
     * @return 结果
     */
    public int insertCabinetWxMenu(CabinetWxMenu cabinetWxMenu);

    /**
     * 修改微信菜单
     *
     * @param cabinetWxMenu 微信菜单
     * @return 结果
     */
    public int updateCabinetWxMenu(CabinetWxMenu cabinetWxMenu);

    /**
     * 删除微信菜单
     *
     * @param id 微信菜单主键
     * @return 结果
     */
    public int deleteCabinetWxMenuById(Long id);

    /**
     * 批量删除微信菜单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCabinetWxMenuByIds(Long[] ids);

    /**
     * @Description 查询微信菜单列表所有父级列表
     * @Author LinLuoChen
     * @Date 2023/8/9 14:47
     * @Return java.util.List<Map < Object, Object>>
     **/
    List<Map<String, Object>> selectCabinetWxParentMenuList();
}
