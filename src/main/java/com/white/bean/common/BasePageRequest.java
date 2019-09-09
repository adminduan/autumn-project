package com.white.bean.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duanlsh
 * @description
 * @date 2019/7/18
 */
@Getter
@Setter
public class BasePageRequest {

    private int pageSize = 1;

    private int pageNumber = 10;
}
