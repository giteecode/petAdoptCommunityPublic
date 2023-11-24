import request from '@/utils/request'

// 查询动态列表
export function listCircle(query) {
  return request({
    url: '/adopt/circle/list',
    method: 'get',
    params: query
  })
}

// 查询动态详细
export function getCircle(id) {
  return request({
    url: '/adopt/circle/' + id,
    method: 'get'
  })
}

// 新增动态
export function addCircle(data) {
  return request({
    url: '/adopt/circle',
    method: 'post',
    data: data
  })
}

// 修改动态
export function updateCircle(data) {
  return request({
    url: '/adopt/circle',
    method: 'put',
    data: data
  })
}

// 删除动态
export function delCircle(id) {
  return request({
    url: '/adopt/circle/' + id,
    method: 'delete'
  })
}
