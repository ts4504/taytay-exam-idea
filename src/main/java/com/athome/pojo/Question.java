package com.athome.pojo;


import com.athome.anno.QuestionType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.mysql.cj.xdevapi.JsonArray;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "question",autoResultMap = true)
public class Question {
    @NotNull(groups = Update.class)
    private Integer id;
    private Integer teacherId;
    @NotNull(groups = Add.class,message = "请选择课程！")
    private Integer courseId;
    @QuestionType
    @NotEmpty(message = "题目类型不能为空！")
    private String type;
    @NotEmpty(message = "题目不能为空！")
    private String content;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Options options;

    @NotEmpty(message = "答案不能为空！")
    private String answer;
    private Double score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public interface Update extends Default {}
    public interface Add extends Default {}
}
