import request from '@/utils/request'

// 获取笔记列表
export function listAllNotes() {
  return request({
    url: '/lerp/note/listAll',
    method: 'get'
  })
}

// 查询笔记详细信息
export function getNoteById(noteId) {
  return request({
    url: '/lerp/note/get',
    method: 'get',
    params: { noteId }
  })
}

// 新增笔记
export function addNote(data) {
  return request({
    url: '/lerp/note/add',
    method: 'post',
    data: data
  })
}

// 修改笔记
export function updateNote(data) {
  return request({
    url: '/lerp/note/update',
    method: 'post',
    data: data
  })
}

// 删除笔记
export function deleteNote(noteId) {
  return request({
    url: '/lerp/note/delete',
    method: 'get',
    params: { noteId }
  })
}
