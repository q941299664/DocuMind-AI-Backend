package com.documind.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.documind.system.domain.SysUser;

/**
 * 用户服务接口
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getByUsername(String username);
}
