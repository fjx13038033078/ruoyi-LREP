import request from '@/utils/request'

// 获取购买记录列表
export function listAllPurchases() {
  return request({
    url: '/lerp/purchase/listAll',
    method: 'get'
  })
}

// 查询购买记录详细信息
export function getPurchaseById(purchaseId) {
  return request({
    url: '/lerp/purchase/get',
    method: 'get',
    params: { purchaseId }
  })
}

// 新增购买记录
export function addPurchase(data) {
  return request({
    url: '/lerp/purchase/add',
    method: 'post',
    data: data
  })
}

// 修改购买记录
export function updatePurchase(data) {
  return request({
    url: '/lerp/purchase/update',
    method: 'post',
    data: data
  })
}

// 删除购买记录
export function deletePurchase(purchaseId) {
  return request({
    url: '/lerp/purchase/delete',
    method: 'get',
    params: { purchaseId }
  })
}
