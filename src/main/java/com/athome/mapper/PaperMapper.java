package com.athome.mapper;

import com.athome.pojo.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperMapper{
    @Insert("insert into exam_system.paper (teacher_id, paper_name, total_score, description, course_id,paper_type) values (#{teacherId},#{paperName},#{totalScore},#{description},#{courseId},#{paperType});")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int add (Paper paper);

    @Update("update exam_system.paper set total_score=#{totalScore}  where id=#{id};")
    void update(Paper paper);

    @Select("select * from paper;")
    List<Paper> list();

    @Delete("delete from paper_question where paper_id=#{id};delete from paper where id=#{id};")
    void delete(Integer id);

    @Select("select * from paper where course_id = #{courseId};")
    List<Paper> findByCourse(Integer courseId);
}
