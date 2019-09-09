package com.white.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 菜单类型
 * @author duanlsh
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     *
     */
    PARENT(1, "父节点"),
    CHILD(2, "子节点");

    private Integer status;
    private String message;

}
