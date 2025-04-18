package com.athome.controller;

import com.athome.anno.Status;
import com.athome.pojo.PageBean;
import com.athome.pojo.Result;
import com.athome.pojo.Score;
import com.athome.service.ScoreService;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    //添加一条成绩
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Score score){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer teacherId = (Integer) claim.get("id");
        if(role.equals("student")){
            return Result.error("没有权限！添加失败！");
        }
        score.setTeacherId(teacherId);

        scoreService.add(score);
        return Result.success();
    }

    //获取所有成绩
    @GetMapping("list")
    public Result<PageBean<Score>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer studentId,
            @RequestParam(required = false) Integer teacherId,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) @Status String status
    ){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer id = (Integer) claim.get("id");
        if(role.equals("student")){
            //如果是学生，则查询该学生的成绩
            PageBean<Score> list = scoreService.list(pageNum,pageSize,id,null,courseId,status);
            return Result.success(list);
        }
        //如果是老师，则查看自己创建的课程的学生成绩
        if(role.equals("teacher")){
            PageBean<Score> list = scoreService.list(pageNum,pageSize,null,id,courseId,status);
            return Result.success(list);
        }
        //管理员和老师可以查看所有成绩
        PageBean<Score> list = scoreService.list(pageNum,pageSize,studentId,teacherId,courseId,status);
        return Result.success(list);
    }


    //修改成绩
    @PutMapping()
    public Result update(Integer id,Double score,@Status String status){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        if(role.equals("student")) {
            return Result.error("没有权限，录入失败！");
        }
        scoreService.update(id,score,status);
        return Result.success();
    }
}
