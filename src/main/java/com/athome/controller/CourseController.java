package com.athome.controller;

import com.athome.pojo.Course;
import com.athome.pojo.Result;
import com.athome.service.CourseService;
import com.athome.util.ThreadLocalUtil;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //添加课程
    @PostMapping("/add")
    public Result add(@RequestBody Course course){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer id = (Integer) claim.get("id");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        courseService.add(course,id);
        return Result.success();
    }

    //根据不同的角色获取课程列表
    @GetMapping("/list")
    public Result list(){
        //获取用户的课程列表
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer id = (Integer) claim.get("id");
        //如果是学生，则展示已选的课程
        if(role.equals("student")){
            List<Course> list = courseService.getStudentCourseList(id);
            return Result.success(list);
        }else if(role.equals("teacher")){
            //如果是老师，则展示已创建的课程
            List<Course> list = courseService.getTeacherCourseList(id);
            return Result.success(list);
        }else{
            //管理员，展示所有课程
            List<Course> list = courseService.getAllCourse();
            return Result.success(list);
        }

    }

    @GetMapping("/detail")
    public Result detail(Integer id){
        Course course = courseService.findById(id);
        return Result.success(course);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Course.Update.class) Course course){
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer userId = (Integer) claim.get("id");
        Course byId = courseService.findById(course.getId());
        if(!byId.getTeacherId().equals(userId)){
            return Result.error("没有权限！操作失败");
        }
        courseService.update(course);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@NotNull Integer id){
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer userId = (Integer) claim.get("id");
        Course course = courseService.findById(id);
        //判断是否是创建人
        if(!userId.equals(course.getTeacherId())){
            return Result.error("没有权限！删除失败");
        }
        courseService.delete(id);
        return Result.success();
    }

}
