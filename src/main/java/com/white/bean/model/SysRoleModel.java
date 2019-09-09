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
 * 系统角色信息
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_sys_role")
public class SysRoleModel implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 是否启用 true 启用 false禁用
     */
    private Boolean roleUsing;

    /**
     * 是否锁定 true 锁定 false 无锁
     */
    private Boolean roleLock;

    /**
     * 0 无效 1有效
     */
    private Boolean status;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;


}
