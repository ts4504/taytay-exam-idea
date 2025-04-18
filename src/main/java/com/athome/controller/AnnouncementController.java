package com.athome.controller;

import com.athome.pojo.Announcement;
import com.athome.pojo.Result;
import com.athome.service.AnnouncementService;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/add")
    public Result add(@RequestBody Announcement announcement){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        Integer id = (Integer) claim.get("id");
        if(!role.equals("admin")){
            return Result.error("没有权限！添加失败！");
        }
        announcement.setAdminId(id);
        announcementService.add(announcement);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Announcement> list = announcementService.list();
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody Announcement announcement){
        announcementService.update(announcement);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        announcementService.delete(id);
        return Result.success();
    }


}
