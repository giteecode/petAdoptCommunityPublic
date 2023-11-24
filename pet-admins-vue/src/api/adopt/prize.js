import request from '@/utils/request'

// 查询答题列表
export function listPrize(query) {
  return request({
    url: '/adopt/prize/list',
    method: 'get',
    params: query
  })
}

// 查询答题详细
export function getPrize(id) {
  return request({
    url: '/adopt/prize/' + id,
    method: 'get'
  })
}

// 新增答题
export function addPrize(data) {
  return request({
    url: '/adopt/prize',
    method: 'post',
    data: data
  })
}

// 修改答题
export function updatePrize(data) {
  return request({
    url: '/adopt/prize',
    method: 'put',
    data: data
  })
}

// 删除答题
export function delPrize(id) {
  return request({
    url: '/adopt/prize/' + id,
    method: 'delete'
  })
}
