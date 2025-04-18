package com.athome.mapper;

import com.athome.pojo.PaperQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperQuestionMapper {
    @Insert("insert into exam_system.paper_question (paper_id, question_id, order_in_paper, question_score) values (#{paperId},#{questionId},#{orderInPaper},#{questionScore});")
    int add(PaperQuestion paperQuestion);
}
