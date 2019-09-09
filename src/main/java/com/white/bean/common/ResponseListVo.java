package com.white.bean.common;

import com.white.enums.WhiteExceptionEnum;
import lombok.Data;

import java.util.List;

/**
 * @author k
 * @description 封装成 layui需要的格式
 * @date 2019/8/12
 */
@Data
public class ResponseListVo<T>{
    private Integer code;
    private String message;
    private List<T> data;
    private Long count;

    public ResponseListVo(List data, long count){
        this.code = WhiteExceptionEnum.SUCCESS.getCode();
        this.message = WhiteExceptionEnum.SUCCESS.getMessage();
        this.data = data;
        this.count = count;
    }
}
