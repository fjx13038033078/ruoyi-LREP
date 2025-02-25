import request from '@/utils/request'

// 获取课程列表
export function listAllCourses() {
  return request({
    url: '/lerp/course/listAll',
    method: 'get'
  })
}

// 查询课程详细信息
export function getCourseById(courseId) {
  return request({
    url: '/lerp/course/get',
    method: 'get',
    params: { courseId }
  })
}

// 新增课程
export function addCourse(data) {
  return request({
    url: '/lerp/course/add',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateCourse(data) {
  return request({
    url: '/lerp/course/update',
    method: 'post',
    data: data
  })
}

// 删除课程
export function deleteCourse(courseId) {
  return request({
    url: '/lerp/course/delete',
    method: 'get',
    params: { courseId }
  })
}
