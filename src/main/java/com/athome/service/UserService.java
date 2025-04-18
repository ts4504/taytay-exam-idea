package com.athome.service;

import com.athome.pojo.Teacher;
import com.athome.pojo.User;
import com.athome.pojo.UserAllInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 新增一个账号
     * @param username
     * @param password
     * @param role
     */
    void register(String username, String password, String role);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserAllInfo findById(Integer id);

    /**
     * 更新用户信息
     * @param user
     */
    void updateInfo(UserAllInfo user);

    /**
     * 更新密码
     * @param username
     * @param newPwd
     */
    void updatePwd(String username, String newPwd);

    /**
     * 获取所有用户信息
     * @return 一个用户列表
     */
    List<User> getAllUser();

    /**
     * 获取所有教师信息
     * @return 教师对象集合
     */
    List<UserAllInfo> getAllTeacher();

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 获取所有学生信息
     * @return
     */
    List<UserAllInfo> getAllStudent();

    /**
     * 获取管理元列表
     * @return
     */
    List<UserAllInfo> getAllAdmin();


    /**
     * 根据id删除用户
     * @param id
     */
    void delete(Integer id);
}
