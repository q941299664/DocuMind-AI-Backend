package com.documind.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.documind.system.domain.SysUser;
import com.documind.system.mapper.SysUserMapper;
import com.documind.system.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .last("limit 1"));
    }
}
