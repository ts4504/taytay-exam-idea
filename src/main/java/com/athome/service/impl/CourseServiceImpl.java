package com.athome.service.impl;

import com.athome.mapper.CourseMapper;
import com.athome.pojo.Course;
import com.athome.service.CourseService;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void add(Course course,Integer id) {
        course.setTeacherId(id);
        courseMapper.add(course);
    }

    @Override
    public List<Course> getStudentCourseList(Integer id) {
        return courseMapper.getStudentCourseList(id);
    }

    @Override
    public List<Course> getTeacherCourseList(Integer id) {
        return courseMapper.getTeacherCourseList(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.findById(id);
    }

    @Override
    public void update(Course course) {
        courseMapper.update(course);
    }

    @Override
    public void delete(Integer id) {
        courseMapper.delete(id);
    }
}
