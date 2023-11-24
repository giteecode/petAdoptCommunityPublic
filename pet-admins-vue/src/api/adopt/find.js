import request from '@/utils/request'

// 查询寻宠列表
export function listFind(query) {
  return request({
    url: '/adopt/find/list',
    method: 'get',
    params: query
  })
}

// 查询寻宠详细
export function getFind(id) {
  return request({
    url: '/adopt/find/' + id,
    method: 'get'
  })
}

// 新增寻宠
export function addFind(data) {
  return request({
    url: '/adopt/find',
    method: 'post',
    data: data
  })
}

// 修改寻宠
export function updateFind(data) {
  return request({
    url: '/adopt/find',
    method: 'put',
    data: data
  })
}

// 删除寻宠
export function delFind(id) {
  return request({
    url: '/adopt/find/' + id,
    method: 'delete'
  })
}
