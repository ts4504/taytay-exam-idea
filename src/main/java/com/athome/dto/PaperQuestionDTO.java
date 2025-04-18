package com.athome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperQuestionDTO {
    private Integer paperId;
    private Integer questionId;
    private Double questionScore;
    private String paperName;
    private String paperType;
    private String description;
}
