package com.athome.service;

import com.athome.dto.AutoPaperRequest;
import com.athome.dto.ManualPaperRequest;
import com.athome.pojo.Paper;
import com.athome.pojo.Result;

import java.util.List;

public interface PaperService {


    /**
     * 手动组卷，并返回试卷
     * @param request
     * @return
     */
    Paper manualAddPaper(ManualPaperRequest request);

    /**
     * 自动组卷，并返回试卷
     * @param request
     * @return
     */
    Paper autoAddPaper(AutoPaperRequest request);

    /**
     * 获取试卷列表
     * @return
     */
    List<Paper> list();

    /**
     * 删除试卷
     * @param id
     */
    void delete(Integer id);


    /**
     * 根据课程查找试卷
     * @param courseId
     * @return
     */
    List<Paper> findByCourse(Integer courseId);
}
