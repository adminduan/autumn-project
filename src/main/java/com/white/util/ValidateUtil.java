package com.white.util;

import com.white.enums.WhiteExceptionEnum;
import com.white.exception.WhiteException;

/**
 * @author duanlsh
 * @description 参数验证
 * @date 2019/7/18
 */
public class ValidateUtil {

    public static void isTrue(boolean result, WhiteExceptionEnum whiteDewExceptionEnum){
        if(result){
            throw new WhiteException(whiteDewExceptionEnum);
        }
    }
}
