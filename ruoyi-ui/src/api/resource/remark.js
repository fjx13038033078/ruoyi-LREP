import request from '@/utils/request'

// 获取所有评论列表
export function listAllRemarks(query) {
  return request({
    url: '/lerp/remark/listAll',
    method: 'get',
    params: query
  })
}

// 获取资源评论列表
export function listRemarksByResourceId(resourceId) {
  return request({
    url: '/lerp/remark/list',
    method: 'get',
    params: { resourceId }
  })
}

// 获取笔记评论列表
export function listRemarksByNoteId(noteId) {
  return request({
    url: '/lerp/remark/listByNoteId',
    method: 'get',
    params: { noteId }
  })
}

// 新增评论
export function addRemark(data) {
  return request({
    url: '/lerp/remark/add',
    method: 'post',
    data: data
  })
}

// 删除评论
export function deleteRemark(remarkId) {
  return request({
    url: '/lerp/remark/delete',
    method: 'get',
    params: { remarkId }
  })
}
