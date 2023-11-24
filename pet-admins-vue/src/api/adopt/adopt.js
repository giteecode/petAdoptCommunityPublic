import request from '@/utils/request'

// 查询宠物领养列表
export function listAdopt(query) {
  return request({
    url: '/adopt/adopt/list',
    method: 'get',
    params: query
  })
}

// 查询宠物领养详细
export function getAdopt(id) {
  return request({
    url: '/adopt/adopt/' + id,
    method: 'get'
  })
}

// 新增宠物领养
export function addAdopt(data) {
  return request({
    url: '/adopt/adopt',
    method: 'post',
    data: data
  })
}

// 修改宠物领养
export function updateAdopt(data) {
  return request({
    url: '/adopt/adopt',
    method: 'put',
    data: data
  })
}

// 删除宠物领养
export function delAdopt(id) {
  return request({
    url: '/adopt/adopt/' + id,
    method: 'delete'
  })
}
