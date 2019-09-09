package com.white.constants;


import com.white.bean.vo.SimpleUserVo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author duanlsh
 * @description 公共静态信息
 * @date 2019/7/16
 */
public class Constants {

    public static Integer JWT_TTL =  6 * 60 * 60;
    public static Integer JWT_TTL_MILLI = JWT_TTL * 1000;

    public static String HEADER_TOKEN = "token";

    /**
     * 父级id
     */
    public static Integer PARENT_ID = 0;

    public static Map<String, SimpleUserVo> sessionMap = new ConcurrentHashMap<>();

    private static ThreadLocal<SimpleUserVo> threadLocal = new ThreadLocal<>();

    public static void put(SimpleUserVo simpleUserVo) {
        threadLocal.set(simpleUserVo);
    }

    public static SimpleUserVo get() {
        return threadLocal.get();
    }
}
