/*
 * MIT License
 *
 * Copyright (c) 2021 James Zow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software
 */
package ai.james.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;

/**
 * 通用分页工具
 */
@Data
public class BasePageQuery {

    @ApiModelProperty(value = "当前页", example = "1")
    private transient Integer pageNum = 1;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private transient Integer pageSize = 10;


    /**
     * 获取页面对象
     *
     * @return
     */
    public <E> Page<E> buildMybatisPage() {
        Page<E> page = new Page<>();

        if (pageNum != null) {
            page.setCurrent(pageNum);
        }

        if (pageSize != null) {
            page.setSize(pageSize);
        }

        return page;
    }

    public <E> Page<E> buildMybatisPage(OrderItem... orderItem) {
        Page<E> page = new Page<>();

        if (pageNum != null) {
            page.setCurrent(pageNum);
        }

        if (pageSize != null) {
            page.setSize(pageSize);
        }

        if(orderItem.length>0){
            page.setOrders(Arrays.asList(orderItem));
        }


        return page;
    }
}
