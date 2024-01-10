package com.tools.web.controller.cabinet;

import com.tools.cabinet.domain.CabinetType;
import com.tools.cabinet.service.ICabinetTypeService;
import com.tools.common.annotation.Log;
import com.tools.common.core.controller.BaseController;
import com.tools.common.core.domain.AjaxResult;
import com.tools.common.enums.BusinessType;
import com.tools.common.utils.SecurityUtils;
import com.tools.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Description 分类表
 * @Author LinLuoChen
 * @Date 2023/4/4 16:59
 **/
@RestController
@RequestMapping("/cabinet/type")
public class CabinetTypeController extends BaseController {
    @Autowired
    private ICabinetTypeService cabinetTypeService;

    /**
     * 查询类型列表
     */
    @PreAuthorize("@ss.hasPermi('cabinet:type:list')")
    @GetMapping("/list")
    public AjaxResult list(CabinetType cabinetType) {
        List<CabinetType> list = cabinetTypeService.selectCabinetTypeList(cabinetType);
        return success(list);
    }

    /**
     * 导出类型列表
     */
    @PreAuthorize("@ss.hasPermi('cabinet:type:export')")
    @Log(title = "类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CabinetType cabinetType) {
        List<CabinetType> list = cabinetTypeService.selectCabinetTypeList(cabinetType);
        ExcelUtil<CabinetType> util = new ExcelUtil<CabinetType>(CabinetType.class);
        util.exportExcel(response, list, "类型数据");
    }

    /**
     * 获取类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('cabinet:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(cabinetTypeService.selectCabinetTypeById(id));
    }

    /**
     * 新增类型
     */
    @PreAuthorize("@ss.hasPermi('cabinet:type:add')")
    @Log(title = "类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CabinetType cabinetType) {
        // 获取登录用户信息
        String username = SecurityUtils.getUsername();
        cabinetType.setCreateName(username);
        cabinetType.setCreateTime(new Date());
        // 判断父级 ID 是否有值
        if (cabinetType.getParentId() == null || cabinetType.getParentId() == 0) {
            cabinetType.setParentId(0L);
        }
        return toAjax(cabinetTypeService.insertCabinetType(cabinetType));
    }

    /**
     * 修改类型
     */
    @PreAuthorize("@ss.hasPermi('cabinet:type:edit')")
    @Log(title = "类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CabinetType cabinetType) {
        // 获取登录用户信息
        String username = SecurityUtils.getUsername();
        cabinetType.setUpdateName(username);
        cabinetType.setUpdateTime(new Date());
        return toAjax(cabinetTypeService.updateCabinetType(cabinetType));
    }

    /**
     * 删除类型
     */
    @PreAuthorize("@ss.hasPermi('cabinet:type:remove')")
    @Log(title = "类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cabinetTypeService.deleteCabinetTypeByIds(ids));
    }
}
