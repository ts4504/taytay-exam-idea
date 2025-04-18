package com.athome.service;

import com.athome.pojo.Exam;

import java.util.List;

public interface ExamService {
    /**
     * 新增考试
     * @param exam
     */
    void add(Exam exam);

    /**
     * 获取当前教师创建的所有考试
     * @param id
     * @return
     */
    List<Exam> getTeacherExamList(Integer id);

    /**
     * 获取当前学生已选课程的所有考试
     * @param id
     * @return
     */
    List<Exam> getStudentExamList(Integer id);

    /**
     * 获取所有考试
     * @return
     */
    List<Exam> getAllExam();

}
