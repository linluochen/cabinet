import request from '@/utils/request'

// 查询微信菜单列表
export function listMenu(query) {
  return request({
    url: '/cabinet/wx/menu/list',
    method: 'get',
    params: query
  })
}

// 查询微信菜单详细
export function getMenu(id) {
  return request({
    url: '/cabinet/wx/menu/' + id,
    method: 'get'
  })
}

// 新增微信菜单
export function addMenu(data) {
  return request({
    url: '/cabinet/wx/menu',
    method: 'post',
    data: data
  })
}

// 修改微信菜单
export function updateMenu(data) {
  return request({
    url: '/cabinet/wx/menu',
    method: 'put',
    data: data
  })
}

// 删除微信菜单
export function delMenu(id) {
  return request({
    url: '/cabinet/wx/menu/' + id,
    method: 'delete'
  })
}

// 查询微信菜单列表所有父级列表
export function listParentList() {
  return request({
    url: '/wx/wechat/parentMenuList',
    method: 'get'
  })
}
