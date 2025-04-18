package com.athome.mapper;

import com.athome.pojo.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Insert("insert into score (student_id, exam_id, teacher_id,course_id, score,status) values (#{studentId},#{examId},#{teacherId},#{courseId},#{score},#{status});")
    void add(Score score);

    List<Score> list( Integer studentId, Integer teacherId,Integer courseId, String status);

    @Update("update score set score = #{score},status=#{status} where id=#{id};")
    void update(Integer id,Double score,String status);
}
