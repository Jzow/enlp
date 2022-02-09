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
package ai.james.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 字段自动填充
 *
 * @link https://mp.baomidou.com/guide/auto-fill-metainfo.html
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充创建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {


        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        try {
            this.strictInsertFill(metaObject, "createBy", () -> "admin", String.class);
        }catch (RuntimeException exception){
            this.strictInsertFill(metaObject, "createBy", () -> "admin", String.class);
        }


    }

    /**
     * 更新填充更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        try {
            this.strictUpdateFill(metaObject, "updateBy",  () -> "admin", String.class);
        }catch (RuntimeException exception){
            this.strictUpdateFill(metaObject, "updateBy", () -> "admin", String.class);
        }

    }

}
