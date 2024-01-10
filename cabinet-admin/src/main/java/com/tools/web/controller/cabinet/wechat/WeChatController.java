package com.tools.web.controller.cabinet.wechat;

import com.tools.cabinet.domain.CabinetWxConf;
import com.tools.cabinet.domain.CabinetWxMenu;
import com.tools.cabinet.service.ICabinetWxConfService;
import com.tools.cabinet.service.ICabinetWxMenuService;
import com.tools.common.annotation.Anonymous;
import com.tools.common.core.controller.BaseController;
import com.tools.common.core.domain.AjaxResult;
import com.tools.common.utils.StringUtils;
import com.tools.common.wxtool.wechat.apis.AccessTokenAPI;
import com.tools.common.wxtool.wechat.pojo.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Title: WeChatController
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 微信/企业微信相关 相关 API 配置类
 * @Date 2023/4/20 10:36
 */
@Anonymous
@RestController
@RequestMapping("/wx/wechat")
public class WeChatController extends BaseController {

    @Autowired
    private ICabinetWxConfService cabinetWxConfService;

    @Autowired
    private ICabinetWxMenuService cabinetWxMenuService;

    /**
     * @Description 查询微信菜单列表所有父级列表
     * @Author LinLuoChen
     * @Date 2023/8/9 14:45
     * @Param [cabinetWxMenu]
     * @Return com.tools.common.core.page.TableDataInfo
     **/
    @Anonymous
    @GetMapping("/parentMenuList")
    public AjaxResult parentMenuList() {
        List<Map<String, Object>> list = cabinetWxMenuService.selectCabinetWxParentMenuList();
        return success(list);
    }

    /**
     * @Description 查询微信菜单列表
     * @Author LinLuoChen
     * @Date 2023/8/9 14:45
     * @Param [cabinetWxMenu]
     * @Return com.tools.common.core.page.TableDataInfo
     **/
    @GetMapping("/menuList")
    public AjaxResult menuList(CabinetWxMenu cabinetWxMenu) {
        List<CabinetWxMenu> list = cabinetWxMenuService.selectCabinetWxMenuList(cabinetWxMenu);
        return success(list);
    }

    /**
     * @Description 查询配置列表
     * @Author LinLuoChen
     * @Date 2023/8/9 14:45
     * @Param [cabinetWxConf]
     * @Return com.tools.common.core.domain.AjaxResult
     **/
    @GetMapping("/confList")
    public AjaxResult confList(CabinetWxConf cabinetWxConf) {
        List<CabinetWxConf> list = cabinetWxConfService.selectCabinetWxConfList(cabinetWxConf);
        return AjaxResult.success(list);
    }

    /* -------------------------------------------- token start  -------------------------------------------- */


    /**
     * @Description 获取 AccessToken 信息
     * @Author LinLuoChen
     * @Date 2023/7/25 10:39
     * @Param [id:微信配置（config）ID]
     * @Return com.tools.common.core.domain.AjaxResult
     **/
    @GetMapping("/getAccessToken")
    public AjaxResult confList(Long id) {
        CabinetWxConf conf = cabinetWxConfService.selectCabinetWxConfById(id);
        AccessToken accessToken = AccessTokenAPI.getAccessToken(conf.getAppid(), conf.getSecret());
        // 修改微信配置表
        if (StringUtils.isNotNull(accessToken)) {
            conf.setAccessToken(accessToken.getToken());
            conf.setExpiresIn(accessToken.getExpiresIn());
            cabinetWxConfService.updateCabinetWxConf(conf);
        }
        return AjaxResult.success(accessToken);
    }

    /* -------------------------------------------- token end  -------------------------------------------- */

}
