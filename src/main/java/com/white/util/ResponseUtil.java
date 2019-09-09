package com.white.util;


import com.white.bean.common.ResponseVo;
import com.white.enums.WhiteExceptionEnum;

import java.text.MessageFormat;

/**
 * @author duanlsh
 * @description 结果对象
 * @date 2019/7/15
 */
public abstract class ResponseUtil {


    public static ResponseVo success(){
        return success(null);
    }

    public static ResponseVo success(Object data, String... param){
        return packageResponseVo(WhiteExceptionEnum.SUCCESS, data, param);
    }

    public static ResponseVo fail(){
        return fail(WhiteExceptionEnum.FAIL, null);
    }


    public static ResponseVo fail(WhiteExceptionEnum whiteDewExceptionEnum, String...param){
        return packageResponseVo(whiteDewExceptionEnum, null, param);
    }


    private static ResponseVo packageResponseVo(WhiteExceptionEnum whiteDewExceptionEnum, Object data, String[] param){
        String message = param != null && param.length > 0 ? MessageFormat.format(whiteDewExceptionEnum.getMessage(), param) : whiteDewExceptionEnum.getMessage();
        return new ResponseVo(whiteDewExceptionEnum.getCode(), message, data);
    }

}
