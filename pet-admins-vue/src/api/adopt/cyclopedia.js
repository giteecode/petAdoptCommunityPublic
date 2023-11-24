import request from '@/utils/request'

// 查询宠物科普列表
export function listCyclopedia(query) {
  return request({
    url: '/adopt/cyclopedia/list',
    method: 'get',
    params: query
  })
}

// 查询宠物科普详细
export function getCyclopedia(id) {
  return request({
    url: '/adopt/cyclopedia/' + id,
    method: 'get'
  })
}

// 新增宠物科普
export function addCyclopedia(data) {
  return request({
    url: '/adopt/cyclopedia',
    method: 'post',
    data: data
  })
}

// 修改宠物科普
export function updateCyclopedia(data) {
  return request({
    url: '/adopt/cyclopedia',
    method: 'put',
    data: data
  })
}

// 删除宠物科普
export function delCyclopedia(id) {
  return request({
    url: '/adopt/cyclopedia/' + id,
    method: 'delete'
  })
}
