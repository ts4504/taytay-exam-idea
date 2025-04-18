package com.athome.service.impl;

import com.athome.mapper.ScoreMapper;
import com.athome.pojo.PageBean;
import com.athome.pojo.Score;
import com.athome.service.ScoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public void add(Score score) {
        scoreMapper.add(score);
    }

    @Override
    public PageBean<Score> list(Integer pageNum,Integer pageSize, Integer studentId, Integer teacherId, Integer courseId, String status) {
        PageBean<Score> scorePageBean = new PageBean<>();

        PageHelper.startPage(pageNum,pageSize);
        List<Score> list = scoreMapper.list(studentId,teacherId,courseId,status);
        Page<Score> page = (Page<Score>) list;

        scorePageBean.setTotal(page.getTotal());
        scorePageBean.setItems(page.getResult());
        return scorePageBean;
    }

    @Override
    public void update(Integer id,Double score,String status) {
        scoreMapper.update(id,score,status);
    }
}
