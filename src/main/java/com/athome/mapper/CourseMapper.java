package com.athome.mapper;

import com.athome.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    //添加课程
    @Insert("insert into exam_system.course (course_name, description, teacher_id,image) values (#{courseName},#{description},#{teacherId},#{image});")
    void add(Course course);

    //查询学生已选的课程
    @Select("select c.id,course_name,description,teacher_id,create_time,image from student_course sc inner join course c on sc.course_id = c.id left join teacher t on c.teacher_id = t.id where sc.student_id=#{id};")
    List<Course> getStudentCourseList(Integer id);

    //查询老师创建的课程
    @Select("select * from course where teacher_id=#{id};")
    List<Course> getTeacherCourseList(Integer id);

    @Select("select * from course;")
    List<Course> getAllCourse();

    @Select("select * from course where id=#{id};")
    Course findById(Integer id);

    @Update("update course set course_name = #{courseName},description=#{description},image=#{image} where id=#{id};")
    void update(Course course);

    @Delete("delete from course where id=#{id};")
    void delete(Integer id);
}
