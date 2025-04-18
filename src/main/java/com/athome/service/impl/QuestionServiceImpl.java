package com.athome.service.impl;

import com.athome.dto.PaperQuestionDTO;
import com.athome.mapper.PaperQuestionMapper;
import com.athome.mapper.QuestionMapper;
import com.athome.pojo.PageBean;
import com.athome.pojo.Question;
import com.athome.service.QuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    private PaperQuestionMapper paperQuestionMapper;

    @Override
    public void add(Question question) {
        questionMapper.add(question);
    }

    @Override
    public Question findById(Integer id) {
        return questionMapper.findById(id);
    }

    @Override
    public PageBean<Question> list(Integer pageNum, Integer pageSize, Integer courseId, String type) {
        PageBean<Question> questionPageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Question> list = questionMapper.list(courseId,type);
        Page<Question> page = (Page<Question>) list;
        questionPageBean.setTotal(page.getTotal());
        questionPageBean.setItems(page.getResult());
        return questionPageBean;
    }

    @Override
    public void update(Question question) {
        questionMapper.update(question);
    }

    @Override
    public void delete(Integer id) {
        questionMapper.delete(id);
    }

    @Override
    public List<Question> findByCourse(Integer courseId) {
        return questionMapper.findByCourse(courseId);
    }

    @Override
    public List<Question> findByPaper(Integer paperId) {
        return questionMapper.findByPaper(paperId);
    }

}
