package com.white.bean.vo;


import lombok.Data;

import java.util.Set;


/**
 * 登录结果
 */
@Data
public class SysLoginVo {

    private String token;

    private SimpleUserVo simpleUser;

    private Set<Integer> roleIdList;
}
