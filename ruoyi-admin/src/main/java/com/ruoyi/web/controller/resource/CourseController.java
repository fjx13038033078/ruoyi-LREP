package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Course;
import com.ruoyi.resource.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/course")
public class CourseController extends BaseController {
    private final CourseService courseService;

    /**
     * 获取所有课程列表
     *
     * @return 课程列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllCourses() {
        startPage();
        List<Course> courses = courseService.getAllCourses();
        return getDataTable(courses);
    }

    /**
     * 根据课程ID获取课程详情
     *
     * @param courseId 课程ID
     * @return 课程详情
     */
    @GetMapping("/get")
    public AjaxResult getCourseById(@RequestParam Long courseId) {
        return success(courseService.getCourseById(courseId));
    }

    /**
     * 添加课程
     *
     * @param course 课程对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addCourse(@RequestBody Course course) {
        return toAjax(courseService.addCourse(course));
    }

    /**
     * 更新课程信息
     *
     * @param course 课程对象
     * @return 操作结果
     */
    @PostMapping("/update")
    public AjaxResult updateCourse(@RequestBody Course course) {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程
     *
     * @param courseId 课程ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteCourse(@RequestParam Long courseId) {
        return toAjax(courseService.deleteCourse(courseId));
    }

}
