package com.tools.web.controller.cabinet;

import com.tools.cabinet.domain.CabinetWxConf;
import com.tools.cabinet.domain.CabinetWxMenu;
import com.tools.cabinet.service.ICabinetWxConfService;
import com.tools.cabinet.service.ICabinetWxMenuService;
import com.tools.common.annotation.Log;
import com.tools.common.core.controller.BaseController;
import com.tools.common.core.domain.AjaxResult;
import com.tools.common.core.page.TableDataInfo;
import com.tools.common.enums.BusinessType;
import com.tools.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 微信配置Controller
 *
 * @author tools
 * @date 2023-04-21
 */
@RestController
@RequestMapping("/cabinet/wx/conf")
public class CabinetWxConfController extends BaseController {

    @Autowired
    private ICabinetWxConfService cabinetWxConfService;

    @Autowired
    private ICabinetWxMenuService cabinetWxMenuService;

    /**
     * 查询微信配置列表
     */
    @PreAuthorize("@ss.hasPermi('cabinet:conf:list')")
    @GetMapping("/list")
    public TableDataInfo list(CabinetWxConf cabinetWxConf) {
        startPage();
        List<CabinetWxConf> list = cabinetWxConfService.selectCabinetWxConfList(cabinetWxConf);
        return getDataTable(list);
    }

    /**
     * 导出微信配置列表
     */
    @PreAuthorize("@ss.hasPermi('cabinet:conf:export')")
    @Log(title = "微信配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CabinetWxConf cabinetWxConf) {
        List<CabinetWxConf> list = cabinetWxConfService.selectCabinetWxConfList(cabinetWxConf);
        ExcelUtil<CabinetWxConf> util = new ExcelUtil<CabinetWxConf>(CabinetWxConf.class);
        util.exportExcel(response, list, "微信配置数据");
    }

    /**
     * 获取微信配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('cabinet:conf:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(cabinetWxConfService.selectCabinetWxConfById(id));
    }

    /**
     * 新增微信配置
     */
    @PreAuthorize("@ss.hasPermi('cabinet:conf:add')")
    @Log(title = "微信配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CabinetWxConf cabinetWxConf) {
        return toAjax(cabinetWxConfService.insertCabinetWxConf(cabinetWxConf));
    }

    /**
     * 修改微信配置
     */
    @PreAuthorize("@ss.hasPermi('cabinet:conf:edit')")
    @Log(title = "微信配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CabinetWxConf cabinetWxConf) {
        return toAjax(cabinetWxConfService.updateCabinetWxConf(cabinetWxConf));
    }

    /**
     * 删除微信配置
     */
    @PreAuthorize("@ss.hasPermi('cabinet:conf:remove')")
    @Log(title = "微信配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cabinetWxConfService.deleteCabinetWxConfByIds(ids));
    }

    /** -------------------------------------------------- 关联其他表 --------------------------------------------------**/
    /**
     * @Description 查询菜单的下拉列表
     * @Author LinLuoChen
     * @Date 2023/7/24 14:41
     * @Param [cabinetWxMenu]
     * @Return com.tools.common.core.page.TableDataInfo
     **/
    @GetMapping("/menuList")
    public AjaxResult list(CabinetWxMenu cabinetWxMenu) {
        List<CabinetWxMenu> list = cabinetWxMenuService.selectCabinetWxMenuList(cabinetWxMenu);
        return AjaxResult.success(list);
    }


}
