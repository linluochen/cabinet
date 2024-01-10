import request from '@/utils/request'

// 查询类型列表
export function listType(query) {
  return request({
    url: '/cabinet/type/list',
    method: 'get',
    params: query
  })
}

// 查询类型详细
export function getType(id) {
  return request({
    url: '/cabinet/type/' + id,
    method: 'get'
  })
}

// 新增类型
export function addType(data) {
  return request({
    url: '/cabinet/type',
    method: 'post',
    data: data
  })
}

// 修改类型
export function updateType(data) {
  return request({
    url: '/cabinet/type',
    method: 'put',
    data: data
  })
}

// 删除类型
export function delType(id) {
  return request({
    url: '/cabinet/type/' + id,
    method: 'delete'
  })
}
