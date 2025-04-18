package com.athome.mapper;

import com.athome.pojo.Exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamMapper {
    //添加考试
    @Insert("insert into exam (exam_name, course_id, paper_id, start_time, end_time, location, duration, create_user) values (#{examName},#{courseId},#{paperId},#{startTime},#{endTime},#{location},#{duration},#{createUser})")
    void add(Exam exam);

    @Select("select * from exam where create_user=#{id};")
    List<Exam> getTeacherExamList(Integer id);

    //通过课程id查找相关考试
    @Select("select * from exam where course_id=#{courseId};")
    List<Exam> findByCourseId(Integer courseId);

    //（admin）获取所有考试
    @Select("select * from exam;")
    List<Exam> list();

}
