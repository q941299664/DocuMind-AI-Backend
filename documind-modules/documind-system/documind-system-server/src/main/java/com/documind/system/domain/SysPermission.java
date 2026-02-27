package com.documind.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限菜单表
 *
 * @author DocuMind
 * @since 2026-02-26
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 权限名称 (查看用户, 上传文档)
     */
    private String name;

    /**
     * 权限标识 (user:list, doc:upload)
     */
    private String permissionCode;

    /**
     * 类型 (1:菜单, 2:按钮/API)
     */
    private Integer type;

    /**
     * 路由地址 (仅菜单)
     */
    private String path;

    /**
     * 前端组件路径 (仅菜单)
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}
