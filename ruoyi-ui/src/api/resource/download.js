import request from '@/utils/request'

// 获取下载记录列表
export function listAllDownloads() {
  return request({
    url: '/lerp/download/listAll',
    method: 'get'
  })
}

// 新增下载记录
export function addDownload(data) {
  return request({
    url: '/lerp/download/add',
    method: 'post',
    data: data
  })
}

// 删除下载记录
export function deleteDownload(downloadId) {
  return request({
    url: '/lerp/download/delete',
    method: 'get',
    params: { downloadId }
  })
}
