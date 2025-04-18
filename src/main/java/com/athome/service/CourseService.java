package com.athome.service;

import com.athome.pojo.Course;

import java.util.List;

public interface CourseService {
    //添加课程
    void add(Course course,Integer id);

    //获取学生已选的课程
    List<Course> getStudentCourseList(Integer id);

    //获取老师创建的课程
    List<Course> getTeacherCourseList(Integer id);

    //管理员获取所有课程
    List<Course> getAllCourse();

    //根据id查找课程
    Course findById(Integer id);

    //更新课程信息
    void update(Course course);

    //删除课程
    void delete(Integer id);
}
