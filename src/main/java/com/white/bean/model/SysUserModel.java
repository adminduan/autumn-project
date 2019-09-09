package com.white.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户信息
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_sys_user")
public class SysUserModel implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 显示名
     */
    private String name;

    /**
     * 头像地址
     */
    private String picUrl;

    /**
     * 性别 0 未知 1 男 2 女
     */
    private Integer sex;

    /**
     * 是否启用 true 启用 false 禁用
     */
    private Boolean userUsing;


    /**
     * 用户 锁定 true锁定 false 禁用
     */
    private Boolean userLock;

    /**
     * 0 无效 1有效
     */
    private Boolean status;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;


}
