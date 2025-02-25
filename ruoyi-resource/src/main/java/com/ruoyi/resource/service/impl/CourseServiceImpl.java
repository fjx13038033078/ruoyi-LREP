package com.ruoyi.resource.service.impl;

import com.ruoyi.resource.domain.Course;
import com.ruoyi.resource.mapper.CourseMapper;
import com.ruoyi.resource.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    /**
     * 获取所有课程
     *
     * @return 课程列表
     */
    @Override
    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    /**
     * 根据课程ID获取课程详情
     *
     * @param courseId 课程ID
     * @return 课程详情
     */
    @Override
    public Course getCourseById(Long courseId) {
        return courseMapper.getCourseById(courseId);
    }

    /**
     * 添加课程
     *
     * @param course 课程信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addCourse(Course course) {
        int rows = courseMapper.addCourse(course);
        return rows > 0;
    }

    /**
     * 更新课程信息
     *
     * @param course 课程信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateCourse(Course course) {
        int rows = courseMapper.updateCourse(course);
        return rows > 0;
    }

    /**
     * 删除课程
     *
     * @param courseId 待删除的课程ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteCourse(Long courseId) {
        int rows = courseMapper.deleteCourse(courseId);
        return rows > 0;
    }
}
