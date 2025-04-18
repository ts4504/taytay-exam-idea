package com.athome.mapper;

import com.athome.pojo.Teacher;
import com.athome.pojo.User;
import com.athome.pojo.UserAllInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("select * from exam_system.user where username=#{username};")
    User findByUsername(String username);

    @Insert("insert into exam_system.user (username, password, role) values (#{username},#{password},#{role});")
    void addUser(String username, String password, String role);

    @Insert("insert into exam_system.student (id) values (#{id});")
    void addStudent(Integer id);

    @Insert("insert into exam_system.teacher (id) values (#{id});")
    void addTeacher(Integer id);

    @Select("select user.id,user.role,user.username,user.password,user.email,user.phone,user.create_time as createTime,user.update_time as updateTime, avatar," +
                "student.number as studentNumber,student.grade,student.major,student.class as clazz," +
                "teacher.number as teacherNumber,teacher.department,teacher.title " +
            "from exam_system.user " +
            "left join exam_system.student on user.id = student.id and user.role='student'" +
            "left join exam_system.teacher on user.id = teacher.id and user.role='teacher'" +
            "where user.id=#{id};")
    UserAllInfo findById(Integer id);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("update exam_system.user set username=#{username}, email = #{email},phone=#{phone},avatar=#{avatar},update_time=now()where id=#{id};")
    void updateUser(UserAllInfo user);

    @Update("update exam_system.student set number = #{studentNumber},grade=#{grade},major=#{major},class=#{clazz} where id=#{id};")
    void updateStudent(UserAllInfo user);

    @Update("update exam_system.teacher set number = #{teacherNumber},department=#{department},title=#{title} where id=#{id};")
    void updateTeacher(UserAllInfo user);

    @Update("update exam_system.user set password = #{newPwd} where username=#{username};")
    void updatePwd(String username, String newPwd);

    @Select("select * from user;")
    List<User> getAll();

    @Select("select user.id, username, password, role, email, phone, create_time createTime, update_time updateTime, avatar, number teacherNumber, department, title from user left join teacher on user.id = teacher.id where role = 'teacher';")
    List<UserAllInfo> getAllTeacher();

    @Update("update user set avatar = #{avatarUrl} where id = #{id};")
    void updateAvatar(String avatarUrl, Integer id);

    @Select("select user.id, username, password, role, email, phone, create_time as createTime, update_time updateTime, avatar, number as studentNumber, grade, major, class as clazz from user left join student on user.id = student.id where role = 'student';;")
    List<UserAllInfo> getAllStudent();

    @Select("select * from user where role='admin';")
    List<UserAllInfo> getAllAdmin();

    @Delete("delete from user where id=#{id};")
    void delete(Integer id);

    @Delete("delete from student where id=#{id};")
    void deleteStudent(Integer id);

    @Delete("delete from teacher where id=#{id};")
    void deleteTeacher(Integer id);
}
