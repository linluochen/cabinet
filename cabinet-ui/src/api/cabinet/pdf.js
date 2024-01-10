import request from '@/utils/request'


// 查询部门列表
export function pdfOperator(query) {
  return request({
    url: '/pdf/pdfOperator',
    method: 'get',
    params: query
  })
}
