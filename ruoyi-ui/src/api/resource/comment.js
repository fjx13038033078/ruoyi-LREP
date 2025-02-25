import request from '@/utils/request'

// 获取评论列表
export function listAllComments() {
  return request({
    url: '/lerp/comment/listAll',
    method: 'get'
  })
}

// 查询评论详细信息
export function getCommentById(commentId) {
  return request({
    url: '/lerp/comment/get',
    method: 'get',
    params: { commentId }
  })
}

// 新增评论
export function addComment(data) {
  return request({
    url: '/lerp/comment/add',
    method: 'post',
    data: data
  })
}

// 修改评论
export function updateComment(data) {
  return request({
    url: '/lerp/comment/update',
    method: 'post',
    data: data
  })
}

// 删除评论
export function deleteComment(commentId) {
  return request({
    url: '/lerp/comment/delete',
    method: 'get',
    params: { commentId }
  })
}
