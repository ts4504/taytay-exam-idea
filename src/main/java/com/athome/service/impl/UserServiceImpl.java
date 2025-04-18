package com.athome.service.impl;

import com.athome.mapper.UserMapper;
import com.athome.pojo.Teacher;
import com.athome.pojo.User;
import com.athome.pojo.UserAllInfo;
import com.athome.service.UserService;
import com.athome.util.Md5Util;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password, String role) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.addUser(username,md5String,role);
        User user = findByUsername(username);
        if(user.getRole().equals("student")){
            userMapper.addStudent(user.getId());
        }
        if(user.getRole().equals("teacher")){
            userMapper.addTeacher(user.getId());
        }
    }

    @Override
    public UserAllInfo findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void updateInfo(UserAllInfo user) {
        userMapper.updateUser(user);
        if(user.getRole().equals("student")){
            userMapper.updateStudent(user);
        }
        if(user.getRole().equals("teacher")){
            userMapper.updateTeacher(user);
        }

    }

    @Override
    public void updatePwd(String username, String newPwd) {
        userMapper.updatePwd(username,Md5Util.getMD5String(newPwd));
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAll();
    }

    @Override
    public List<UserAllInfo> getAllTeacher() {
        return userMapper.getAllTeacher();
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer id = (Integer) claim.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public List<UserAllInfo> getAllStudent() {
        return userMapper.getAllStudent();
    }

    @Override
    public List<UserAllInfo> getAllAdmin() {
        return userMapper.getAllAdmin();
    }

    @Override
    public void delete(Integer id) {
        //把用户的角色信息删除掉
        String role = userMapper.findById(id).getRole();
        if(role.equals("student")){
            userMapper.deleteStudent(id);
        }
        if(role.equals("teacher")){
            userMapper.deleteTeacher(id);
        }
        //最后删除用户表
        userMapper.delete(id);
    }


}
