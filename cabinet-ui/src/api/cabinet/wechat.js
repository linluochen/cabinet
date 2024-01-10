import request from '@/utils/request'

// 查询微信菜单列表
export function listMenuList(query) {
  return request({
    url: '/wx/wechat/menuList',
    method: 'get',
    params: query
  })
}

// 查询配置列表
export function listConfList(query) {
  return request({
    url: '/wx/wechat/confList',
    method: 'get',
    params: query
  })
}

// 获取 AccessToken 信息
export function getAccessToken(query) {
  return request({
    url: '/wx/wechat/getAccessToken',
    method: 'get',
    params: query
  })
}

