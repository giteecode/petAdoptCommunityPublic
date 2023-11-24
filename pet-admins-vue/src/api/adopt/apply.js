import request from '@/utils/request'

// 查询领养申请列表
export function listApply(query) {
  return request({
    url: '/adopt/apply/list',
    method: 'get',
    params: query
  })
}

// 查询领养申请详细
export function getApply(id) {
  return request({
    url: '/adopt/apply/' + id,
    method: 'get'
  })
}

// 新增领养申请
export function addApply(data) {
  return request({
    url: '/adopt/apply',
    method: 'post',
    data: data
  })
}

// 修改领养申请
export function updateApply(data) {
  return request({
    url: '/adopt/apply',
    method: 'put',
    data: data
  })
}

// 删除领养申请
export function delApply(id) {
  return request({
    url: '/adopt/apply/' + id,
    method: 'delete'
  })
}
