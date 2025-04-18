package com.athome.mapper;

import com.athome.dto.PaperQuestionDTO;
import com.athome.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (teacher_id,course_id,type,content,options,answer,score) values (#{teacherId},#{courseId},#{type},#{content},#{options,typeHandler=com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler},#{answer},#{score});")
    void add(Question question);

    Question findById(int i);

    List<Question> list(Integer courseId,String type);

    @Update("update question set course_id=#{courseId},type=#{type},content=#{content},options=#{options,typeHandler=com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler},answer=#{answer},score=#{score},update_time=now() where id=#{id};")
    void update(Question question);

    @Delete("delete from question where id=#{id};")
    void delete(Integer id);

    //根据指定课程、指定题目类型，返回指定个数的随机题目
    @Select("select * from exam_system.question where course_id=#{courseId} and type=#{type} order by rand() limit #{count};")
    List<Question> findByLimitRand(Integer courseId, String type, Integer count);

    @Select("select * from question where course_id = #{courseId};")
    List<Question> findByCourse(Integer courseId);

    List<Question> findByPaper(Integer id);
}
