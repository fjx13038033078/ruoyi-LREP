package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:50
 */
@Mapper
public interface CourseMapper {
    /**
     * 获取所有课程
     *
     * @return 所有课程的列表
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
     * @param course 课程对象
     * @return 影响的行数
     */
    int addCourse(Course course);

    /**
     * 更新课程
     *
     * @param course 课程对象
     * @return 影响的行数
     */
    int updateCourse(Course course);

    /**
     * 删除课程
     *
     * @param courseId 课程ID
     * @return 影响的行数
     */
    int deleteCourse(Long courseId);
}
