package com.documind.auth.controller;

import com.documind.auth.model.dto.LoginRequest;
import com.documind.auth.model.dto.LoginResponse;
import com.documind.auth.service.AuthService;
import com.documind.common.core.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "认证接口")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        return Result.success(authService.login(request));
    }

    @Operation(summary = "测试联通")
    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("认证服务访问成功");
    }
}
