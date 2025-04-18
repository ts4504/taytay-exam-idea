package com.athome.service.impl;

import com.athome.mapper.CourseMapper;
import com.athome.mapper.ExamMapper;
import com.athome.pojo.Course;
import com.athome.pojo.Exam;
import com.athome.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Transactional
    @Override
    public void add(Exam exam) {
        examMapper.add(exam);
    }

    @Override
    public List<Exam> getTeacherExamList(Integer id) {
        return examMapper.getTeacherExamList(id);
    }

    @Override
    public List<Exam> getStudentExamList(Integer id) {
        List<Exam> list = new ArrayList<>();
        //获取学生已选的课程id
        List<Course> courseList = courseMapper.getStudentCourseList(id);
        for (Course course : courseList) {
            //通过课程id查找相关考试
            List<Exam> exams =  examMapper.findByCourseId(course.getId());
            list.addAll(exams);
        }
        return list;
    }

    @Override
    public List<Exam> getAllExam() {
        return examMapper.list();
    }
}
