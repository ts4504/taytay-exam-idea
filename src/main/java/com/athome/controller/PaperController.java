package com.athome.controller;

import com.athome.dto.AutoPaperRequest;
import com.athome.dto.ManualPaperRequest;
import com.athome.pojo.Paper;
import com.athome.pojo.Result;
import com.athome.service.PaperService;
import com.athome.util.ThreadLocalUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    //手动组卷
    @PostMapping("/manual")
    public Result<Paper> manualAddPaper(@RequestBody ManualPaperRequest request){
        Map<String,Object> cliam = ThreadLocalUtil.get();
        String role = (String) cliam.get("role");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        Paper paper =  paperService.manualAddPaper(request);
        return Result.success(paper);
    }

    //自动组卷
    @PostMapping("/auto")
    public Result autoAddPaper(@RequestBody AutoPaperRequest request){
        Map<String,Object> cliam = ThreadLocalUtil.get();
        String role = (String) cliam.get("role");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        Paper paper =  paperService.autoAddPaper(request);
        return Result.success(paper);
    }

    //获取试卷列表
    @GetMapping("/list")
    public Result<List<Paper>> getPaperList(){
        Map<String,Object> cliam = ThreadLocalUtil.get();
        String role = (String) cliam.get("role");
        if(role.equals("student")){
            return Result.error("没有权限！");
        }
        List<Paper> list = paperService.list();
        return Result.success(list);
    }

    //根据课程id查找试卷
    @GetMapping("/findByCourse")
    public Result<List<Paper>> getPaperListByCourse(Integer courseId){
        List<Paper> list = paperService.findByCourse(courseId);
        return Result.success(list);
    }

    //删除试卷
    @DeleteMapping
    public Result deletePaper(Integer id){
        paperService.delete(id);
        return Result.success();
    }


}
