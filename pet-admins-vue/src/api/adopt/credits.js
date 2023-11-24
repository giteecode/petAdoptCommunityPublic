import request from '@/utils/request'

// 查询积分列表
export function listCredits(query) {
  return request({
    url: '/adopt/credits/list',
    method: 'get',
    params: query
  })
}

// 查询积分详细
export function getCredits(id) {
  return request({
    url: '/adopt/credits/' + id,
    method: 'get'
  })
}

// 新增积分
export function addCredits(data) {
  return request({
    url: '/adopt/credits',
    method: 'post',
    data: data
  })
}

// 修改积分
export function updateCredits(data) {
  return request({
    url: '/adopt/credits',
    method: 'put',
    data: data
  })
}

// 删除积分
export function delCredits(id) {
  return request({
    url: '/adopt/credits/' + id,
    method: 'delete'
  })
}
