package com.athome.dto;

import com.athome.pojo.PaperQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//手动组卷请求
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManualPaperRequest {
    private Integer teacherId;
    private Integer courseId;
    private String paperName;
    private String description;
    private List<Integer> questions;
}
