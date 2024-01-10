import request from '@/utils/request'

// 查询微信配置列表
export function listConf(query) {
  return request({
    url: '/cabinet/wx/conf/list',
    method: 'get',
    params: query
  })
}

// 查询微信菜单列表
export function listMenuList(query) {
  return request({
    url: '/cabinet/wx/conf/menuList',
    method: 'get',
    params: query
  })
}

// 查询微信配置详细
export function getConf(id) {
  return request({
    url: '/cabinet/wx/conf/' + id,
    method: 'get'
  })
}

// 新增微信配置
export function addConf(data) {
  return request({
    url: '/cabinet/wx/conf',
    method: 'post',
    data: data
  })
}

// 修改微信配置
export function updateConf(data) {
  return request({
    url: '/cabinet/wx/conf',
    method: 'put',
    data: data
  })
}

// 删除微信配置
export function delConf(id) {
  return request({
    url: '/cabinet/wx/conf/' + id,
    method: 'delete'
  })
}
