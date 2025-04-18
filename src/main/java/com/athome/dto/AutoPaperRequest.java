package com.athome.dto;

import com.athome.pojo.Paper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoPaperRequest {
    private Integer teacherId;
    private Integer courseId;
    private String paperName;
    private String description;
    private List<TypeCountDTO> typeCounts;
}
