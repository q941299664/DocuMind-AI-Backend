package com.documind.system.controller;

import com.documind.common.core.exception.BusinessException;
import com.documind.common.core.result.Result;
import com.documind.common.core.result.ResultCode;
import com.documind.system.dto.HelloDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public Result<String> hello() {
        return Result.success("Hello World from DocuMind System Service!");
    }

    @PostMapping("/validate")
    public Result<String> validate(@RequestBody @Validated HelloDTO helloDTO) {
        return Result.success("Hello, " + helloDTO.getName());
    }

    @GetMapping("/error")
    public Result<String> error() {
        throw new BusinessException(ResultCode.FAILED, "这是一条测试业务异常");
    }
}
