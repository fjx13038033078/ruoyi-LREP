import request from '@/utils/request'

// 获取收藏列表
export function listAllFavorites(query) {
  return request({
    url: '/lerp/favorite/listAll',
    method: 'get',
    params: query
  })
}

// 查询收藏详细信息
export function getFavoriteById(favoriteId) {
  return request({
    url: '/lerp/favorite/get',
    method: 'get',
    params: { favoriteId }
  })
}

// 新增收藏
export function addFavorite(data) {
  return request({
    url: '/lerp/favorite/add',
    method: 'post',
    data: data
  })
}

// 修改收藏
export function updateFavorite(data) {
  return request({
    url: '/lerp/favorite/update',
    method: 'post',
    data: data
  })
}

// 删除收藏
export function deleteFavorite(favoriteId) {
  return request({
    url: '/lerp/favorite/delete',
    method: 'get',
    params: { favoriteId }
  })
}
