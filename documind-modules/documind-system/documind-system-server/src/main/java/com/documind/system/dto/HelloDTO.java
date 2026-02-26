package com.documind.system.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class HelloDTO {
    @NotEmpty(message = "姓名不能为空")
    private String name;
}
