import request from '@/utils/request'

// 获取所有评论列表
export function listAllRemarks() {
  return request({
    url: '/lerp/remark/listAll',
    method: 'get'
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
