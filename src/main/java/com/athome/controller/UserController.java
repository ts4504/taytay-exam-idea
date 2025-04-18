package com.athome.controller;

import com.athome.anno.Role;
import com.athome.pojo.Result;
import com.athome.pojo.Teacher;
import com.athome.pojo.User;
import com.athome.pojo.UserAllInfo;
import com.athome.service.UserService;
import com.athome.util.JwtUtil;
import com.athome.util.Md5Util;
import com.athome.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{1,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, @Role String role) {
        //检查用户名是否注册
        User u = userService.findByUsername(username);
        if (u == null) {
            //注册
            userService.register(username, password, role);
            return Result.success();
        } else {
            return Result.error("用户名已存在！");
        }
    }

    //登录
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{1,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //检查一下该用户是否存在
        User loginUser = userService.findByUsername(username);
        if (loginUser == null) {
            return Result.error("用户不存在！");
        }
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
            //返回一个jwt令牌
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            claims.put("role", loginUser.getRole());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误！");
    }

    //获取用户详细信息，包括不同角色各自的信息
    @GetMapping("/info")
    public Result getUserInfo() {
        Map<String, Object> claim = ThreadLocalUtil.get();
        Integer id = (Integer) claim.get("id");
        UserAllInfo user = userService.findById(id);
        return Result.success(user);
    }

    //更新用户的基本信息
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody UserAllInfo user) {
        userService.updateInfo(user);
        return Result.success();
    }

    //更新用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {

        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数！");
        }
        Map<String, Object> claim = ThreadLocalUtil.get();
        String username = (String) claim.get("username");
        User user = userService.findByUsername(username);
        //判断原密码是否正确
        if (user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            if (newPwd.equals(rePwd)) {
                //更改密码
                userService.updatePwd(username, newPwd);
                return Result.success();
            }
            return Result.error("两次密码不一致，修改失败！");
        } else {
            return Result.error("原密码错误，修改失败！");
        }

    }

    //更新用户头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //管理员获取所有用户信息
    @GetMapping("/all")
    public Result getAllUser(){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        if(!role.equals("admin")){
            return Result.error("没有权限，操作失败！");
        }
        List<User> list = userService.getAllUser();
        return Result.success(list);
    }

    //获取教师信息
    @GetMapping("/allTeacher")
    public Result getAllTeacher(){
        List<UserAllInfo> list = userService.getAllTeacher();
        return Result.success(list);
    }

    //获取学生信息
    @GetMapping("/allStudent")
    public Result getAllStudent(){
        List<UserAllInfo> list = userService.getAllStudent();
        return Result.success(list);
    }

    //获取管理员信息
    @GetMapping("/allAdmin")
    public Result getAllAdmin(){
        List<UserAllInfo> list = userService.getAllAdmin();
        return Result.success(list);
    }

    //管理员新增用户
    @PostMapping("/add")
    public Result add(@RequestBody UserAllInfo user){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        //管理员添加用户时，初始密码为123456
        user.setPassword("123456");
        if(!role.equals("admin")){
            return Result.error("无权限！添加失败");
        }
        //先快速注册基本信息
        userService.register(user.getUsername(),user.getPassword(),user.getRole());

        //根据用户名获取用户id
        user.setId(userService.findByUsername(user.getUsername()).getId());

        //再添加其他信息
        userService.updateInfo(user);
        return Result.success();

    }

    //管理员删除用户
    @DeleteMapping
    public Result delete(Integer id){
        //权限检查
        Map<String,Object> claim = ThreadLocalUtil.get();
        String role = (String) claim.get("role");
        if(!role.equals("admin")){
            Result.error("无权限，删除失败！");
        }
        userService.delete(id);
        return Result.success();
    }
}
