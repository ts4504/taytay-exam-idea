package com.athome.controller;

import com.athome.pojo.Exam;
import com.athome.pojo.Result;
import com.athome.service.ExamService;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    //添加考试
    @PostMapping("/add")
    public Result add(@RequestBody Exam exam){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer id = (Integer) claim.get("id");
        if(role.equals("student")){
            return Result.error("没有权限，添加失败！");
        }
        //取开始到结束时间的差值
        Duration duration = Duration.between(exam.getStartTime(),exam.getEndTime());
        exam.setCreateUser(id);
        exam.setDuration((int) duration.toMinutes());
        examService.add(exam);
        return Result.success();
    }

    //获取考试列表
    @GetMapping("/list")
    public Result<List<Exam>> list(){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer id = (Integer) claim.get("id");
        //如果角色是老师，则获取已创建的所有考试
        List<Exam> list;
        if(role.equals("student")){
            //如果角色是学生，则获取已选课程的所有考试
            list =examService.getStudentExamList(id);
            return Result.success(list);
        }else if(role.equals("teacher")){
            //如果是老师，则查看已创建课程的考试
            list = examService.getTeacherExamList(id);
            return Result.success(list);
        }else{
            //如果是管理员，则可以查看所有考试
            list = examService.getAllExam();
            return Result.success(list);
        }
    }
    
}
