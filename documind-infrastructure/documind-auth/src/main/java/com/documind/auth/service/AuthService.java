package com.documind.auth.service;

import com.documind.auth.model.dto.LoginRequest;
import com.documind.auth.model.dto.LoginResponse;
import com.documind.common.core.exception.BusinessException;
import com.documind.common.core.result.ResultCode;
import com.documind.common.security.util.JwtUtil;
import com.documind.system.domain.SysUser;
import com.documind.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final ISysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 用户登录
     */
    public LoginResponse login(LoginRequest request) {
        // 1. 查询用户
        SysUser user = sysUserService.getByUsername(request.getUsername());
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        // 2. 校验密码
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }

        // 3. 校验状态
        if (user.getStatus() == 0) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_LOCKED);
        }

        // 4. 生成 Token (TODO: 获取真实角色)
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), "ROLE_USER");

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .build();
    }
}
