package com.athome.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Options {
    @JsonProperty("A")
    private String A;
    @JsonProperty("B")
    private String B;
    @JsonProperty("C")
    private String C;
    @JsonProperty("D")
    private String D;
}
