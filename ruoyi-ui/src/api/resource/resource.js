import request from '@/utils/request'

// 获取资源列表
export function listAllResources() {
  return request({
    url: '/lerp/resource/listAll',
    method: 'get'
  })
}

// 查询资源详细信息
export function getResourceById(resourceId) {
  return request({
    url: '/lerp/resource/get',
    method: 'get',
    params: { resourceId }
  })
}

// 新增资源
export function addResource(data) {
  return request({
    url: '/lerp/resource/add',
    method: 'post',
    data: data
  })
}

// 修改资源
export function updateResource(data) {
  return request({
    url: '/lerp/resource/update',
    method: 'post',
    data: data
  })
}

// 删除资源
export function deleteResource(resourceId) {
  return request({
    url: '/lerp/resource/delete',
    method: 'get',
    params: { resourceId }
  })
}
