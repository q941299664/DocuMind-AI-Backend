package com.documind.auth.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 登录响应对象
 */
@Data
@Builder
public class LoginResponse {
    private String token;
    private String tokenType;
    private Long expiresIn;
}
