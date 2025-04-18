package com.athome.controller;

import com.athome.anno.QuestionType;
import com.athome.dto.PaperQuestionDTO;
import com.athome.pojo.PageBean;
import com.athome.pojo.PaperQuestion;
import com.athome.pojo.Question;
import com.athome.pojo.Result;
import com.athome.service.QuestionService;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
@Validated
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //新增试题
    @PostMapping
    public Result add(@RequestBody @Validated(Question.Add.class) Question question){
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer userId = (Integer) claim.get("id");
        String role = (String) claim.get("role");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        question.setTeacherId(userId);
        questionService.add(question);
        return Result.success();
    }

    //获取试题列表
    @GetMapping("/list")
    public Result<PageBean<Question>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) @QuestionType String type){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");

        //如果是学生，则拦截
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        PageBean<Question> list = questionService.list(pageNum,pageSize,courseId,type);
        return Result.success(list);
    }

    //获取试题详情
    @GetMapping
    public Result detail(Integer id){
        Question question = questionService.findById(id);
        System.out.println(question);
        return Result.success(question);
    }

    //更新试题
    @PutMapping
    public Result update(@RequestBody @Validated(Question.Update.class) Question question){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        questionService.update(question);
        return Result.success();
    }

    //删除试题
    @DeleteMapping
    public Result delete(Integer id){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        questionService.delete(id);
        return Result.success();
    }

    //获取指定课程的所有试题
    @GetMapping("/findByCourse")
    public Result getQuestionsByCourse(Integer courseId){
        List<Question> list = questionService.findByCourse(courseId);
        return Result.success(list);
    }

    //获取指定试卷的所有试题
    @GetMapping("/findByPaper")
    public Result<List<Question>> getQuestionsByPaper(Integer id){
        List<Question> list = questionService.findByPaper(id);
        return Result.success(list);
    }
}
