package com.athome.pojo;

import com.athome.anno.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAllInfo {
    //User
    @NotNull(groups = {Update.class})
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    @Role
    private String role;
    @Email
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @URL
    private String avatar;

    //Student
    private String studentNumber;
    private String grade;
    private String major;
    private String clazz;

    //Teacher
    private String teacherNumber;
    private String department;
    private String title;

    public interface Update extends Default {}
}
