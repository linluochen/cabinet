import request from '@/utils/request'

// 查询主页目录列表
export function listCatalog(query) {
  return request({
    url: '/cabinet/catalog/list',
    method: 'get',
    params: query
  })
}

// 查询主页目录详细
export function getCatalog(id) {
  return request({
    url: '/cabinet/catalog/' + id,
    method: 'get'
  })
}

// 新增主页目录
export function addCatalog(data) {
  return request({
    url: '/cabinet/catalog',
    method: 'post',
    data: data
  })
}

// 修改主页目录
export function updateCatalog(data) {
  return request({
    url: '/cabinet/catalog',
    method: 'put',
    data: data
  })
}

// 删除主页目录
export function delCatalog(id) {
  return request({
    url: '/cabinet/catalog/' + id,
    method: 'delete'
  })
}
