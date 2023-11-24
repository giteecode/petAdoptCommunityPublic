import request from '@/utils/request'

// 查询小程序用户列表
export function listUser(query) {
  return request({
    url: '/adopt/user/list',
    method: 'get',
    params: query
  })
}

// 查询小程序用户详细
export function getUser(id) {
  return request({
    url: '/adopt/user/' + id,
    method: 'get'
  })
}

// 新增小程序用户
export function addUser(data) {
  return request({
    url: '/adopt/user',
    method: 'post',
    data: data
  })
}

// 修改小程序用户
export function updateUser(data) {
  return request({
    url: '/adopt/user',
    method: 'put',
    data: data
  })
}

// 删除小程序用户
export function delUser(id) {
  return request({
    url: '/adopt/user/' + id,
    method: 'delete'
  })
}
