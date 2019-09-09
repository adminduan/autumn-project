package com.white.bean.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author duanlsh
 * @description 列表和数量
 * @date 2019/7/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCountVo<T> {

    private List<T> list;

    private long count;
}