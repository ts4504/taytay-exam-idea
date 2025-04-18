package com.athome.service;

import com.athome.dto.PaperQuestionDTO;
import com.athome.pojo.PageBean;
import com.athome.pojo.Question;

import java.util.List;

public interface QuestionService {
    //添加试题
    void add(Question question);

    //根据id查找试题
    Question findById(Integer id);

    //获取所有试题
    PageBean<Question> list(Integer pageNum, Integer pageSize, Integer courseId, String type);

    //更新试题
    void update(Question question);

    //删除试题
    void delete(Integer id);

    //获取指定课程的所有题目
    List<Question> findByCourse(Integer courseId);

    //获取指定试卷的所有题目
    List<Question> findByPaper(Integer paperId);
}
