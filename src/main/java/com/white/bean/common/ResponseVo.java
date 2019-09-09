package com.white.bean.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author duanlsh
 * @description 结果对象信息
 * @date 2019/7/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo<T> {

    private Integer code;
    private String message;
    private T data;
}
