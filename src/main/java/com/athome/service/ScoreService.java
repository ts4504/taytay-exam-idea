package com.athome.service;

import com.athome.anno.Status;
import com.athome.pojo.PageBean;
import com.athome.pojo.Score;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ScoreService {
    /**
     * 添加一条成绩
     * @param score
     */
    void add(Score score);

    /**
     * 获取所有成绩
     * @return
     */
    PageBean<Score> list(Integer pageNum,Integer pageSize, Integer studentId, Integer teacherId,Integer courseId, String status);

    /**
     * 修改成绩
     * @param score
     */
    void update(Integer id,Double score,String status);
}
