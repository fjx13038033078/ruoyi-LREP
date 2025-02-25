package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Course;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:00
 */
public interface CourseService {
    /**
     * 获取所有课程
     *
     * @return 课程列表
     */
    List<Course> getAllCourses();

    /**
     * 根据课程ID获取课程详情
     *
     * @param courseId 课程ID
     * @return 课程详情
     */
    Course getCourseById(Long courseId);

    /**
     * 添加课程
     *
     * @param course 待添加的课程信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addCourse(Course course);

    /**
     * 更新课程信息
     *
     * @param course 待更新的课程信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateCourse(Course course);

    /**
     * 删除课程
     *
     * @param courseId 待删除的课程ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteCourse(Long courseId);
}
