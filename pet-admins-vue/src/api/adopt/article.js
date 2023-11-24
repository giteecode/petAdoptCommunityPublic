import request from '@/utils/request'

// 查询文章列表
export function listArticle(query) {
  return request({
    url: '/adopt/article/list',
    method: 'get',
    params: query
  })
}

// 查询文章详细
export function getArticle(id) {
  return request({
    url: '/adopt/article/' + id,
    method: 'get'
  })
}

// 新增文章
export function addArticle(data) {
  return request({
    url: '/adopt/article',
    method: 'post',
    data: data
  })
}

// 修改文章
export function updateArticle(data) {
  return request({
    url: '/adopt/article',
    method: 'put',
    data: data
  })
}

// 删除文章
export function delArticle(id) {
  return request({
    url: '/adopt/article/' + id,
    method: 'delete'
  })
}
