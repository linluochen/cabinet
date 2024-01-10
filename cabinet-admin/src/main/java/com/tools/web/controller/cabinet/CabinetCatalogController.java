package com.tools.web.controller.cabinet;

import com.tools.cabinet.domain.CabinetCatalog;
import com.tools.cabinet.domain.CabinetType;
import com.tools.cabinet.service.ICabinetCatalogService;
import com.tools.cabinet.service.ICabinetTypeService;
import com.tools.common.annotation.Log;
import com.tools.common.core.controller.BaseController;
import com.tools.common.core.domain.AjaxResult;
import com.tools.common.core.page.TableDataInfo;
import com.tools.common.enums.BusinessType;
import com.tools.common.utils.StringUtils;
import com.tools.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description 主页目录表
 * @Author LinLuoChen
 * @Date 2023/4/4 16:59
 **/
@RestController
@RequestMapping("/cabinet/catalog")
public class CabinetCatalogController extends BaseController {
    @Autowired
    private ICabinetCatalogService cabinetCatalogService;

    @Autowired
    private ICabinetTypeService cabinetTypeService;

    /**
     * 查询主页目录列表
     */
    @PreAuthorize("@ss.hasPermi('cabinet:catalog:list')")
    @GetMapping("/list")
    public TableDataInfo list(CabinetCatalog cabinetCatalog) {
        startPage();
        List<CabinetCatalog> list = cabinetCatalogService.selectCabinetCatalogList(cabinetCatalog);
        list.forEach(i -> {
            CabinetType cabinetType = cabinetTypeService.selectCabinetTypeById(Long.valueOf(i.getTypeId()));
            if (cabinetType != null && StringUtils.isNotEmpty(cabinetType.getName())) {
                i.setTypeName(cabinetType.getName());
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出主页目录列表
     */
    @PreAuthorize("@ss.hasPermi('cabinet:catalog:export')")
    @Log(title = "主页目录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CabinetCatalog cabinetCatalog) {
        List<CabinetCatalog> list = cabinetCatalogService.selectCabinetCatalogList(cabinetCatalog);
        ExcelUtil<CabinetCatalog> util = new ExcelUtil<CabinetCatalog>(CabinetCatalog.class);
        util.exportExcel(response, list, "主页目录数据");
    }

    /**
     * 获取主页目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('cabinet:catalog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(cabinetCatalogService.selectCabinetCatalogById(id));
    }

    /**
     * 新增主页目录
     */
    @PreAuthorize("@ss.hasPermi('cabinet:catalog:add')")
    @Log(title = "主页目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CabinetCatalog cabinetCatalog) {
        return toAjax(cabinetCatalogService.insertCabinetCatalog(cabinetCatalog));
    }

    /**
     * 修改主页目录
     */
    @PreAuthorize("@ss.hasPermi('cabinet:catalog:edit')")
    @Log(title = "主页目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CabinetCatalog cabinetCatalog) {
        return toAjax(cabinetCatalogService.updateCabinetCatalog(cabinetCatalog));
    }

    /**
     * 删除主页目录
     */
    @PreAuthorize("@ss.hasPermi('cabinet:catalog:remove')")
    @Log(title = "主页目录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cabinetCatalogService.deleteCabinetCatalogByIds(ids));
    }
}
