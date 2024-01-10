package com.tools.web.controller.cabinet;

import com.tools.cabinet.domain.CabinetWxMenu;
import com.tools.cabinet.service.ICabinetWxMenuService;
import com.tools.common.annotation.Log;
import com.tools.common.core.controller.BaseController;
import com.tools.common.core.domain.AjaxResult;
import com.tools.common.enums.BusinessType;
import com.tools.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 微信菜单Controller
 *
 * @author tools
 * @date 2023-04-23
 */
@RestController
@RequestMapping("/cabinet/wx/menu")
public class CabinetWxMenuController extends BaseController {

    @Autowired
    private ICabinetWxMenuService cabinetWxMenuService;

    /**
     * 查询微信菜单列表
     */
    @PreAuthorize("@ss.hasPermi('wx:menu:list')")
    @GetMapping("/list")
    public AjaxResult list(CabinetWxMenu cabinetWxMenu) {
        List<CabinetWxMenu> list = cabinetWxMenuService.selectCabinetWxMenuList(cabinetWxMenu);
        return success(list);
    }

    /**
     * 导出微信菜单列表
     */
    @PreAuthorize("@ss.hasPermi('wx:menu:export')")
    @Log(title = "微信菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CabinetWxMenu cabinetWxMenu) {
        List<CabinetWxMenu> list = cabinetWxMenuService.selectCabinetWxMenuList(cabinetWxMenu);
        ExcelUtil<CabinetWxMenu> util = new ExcelUtil<CabinetWxMenu>(CabinetWxMenu.class);
        util.exportExcel(response, list, "微信菜单数据");
    }

    /**
     * 获取微信菜单详细信息
     */
    @PreAuthorize("@ss.hasPermi('wx:menu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(cabinetWxMenuService.selectCabinetWxMenuById(id));
    }

    /**
     * 新增微信菜单
     */
    @PreAuthorize("@ss.hasPermi('wx:menu:add')")
    @Log(title = "微信菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CabinetWxMenu cabinetWxMenu) {
        return toAjax(cabinetWxMenuService.insertCabinetWxMenu(cabinetWxMenu));
    }

    /**
     * 修改微信菜单
     */
    @PreAuthorize("@ss.hasPermi('wx:menu:edit')")
    @Log(title = "微信菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CabinetWxMenu cabinetWxMenu) {
        return toAjax(cabinetWxMenuService.updateCabinetWxMenu(cabinetWxMenu));
    }

    /**
     * 删除微信菜单
     */
    @PreAuthorize("@ss.hasPermi('wx:menu:remove')")
    @Log(title = "微信菜单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cabinetWxMenuService.deleteCabinetWxMenuByIds(ids));
    }
}
