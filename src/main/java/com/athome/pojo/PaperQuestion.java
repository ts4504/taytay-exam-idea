package com.athome.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperQuestion {
    private Integer paperId;
    private Integer questionId;
    private Integer orderInPaper;
    private Double questionScore;
}
