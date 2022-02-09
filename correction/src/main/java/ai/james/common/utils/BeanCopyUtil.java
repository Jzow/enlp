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

import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;

/**
 * @author pengmf
 * @since 2021/11/5
 */
public class BeanCopyUtil {


    public static <S, T> T copy(S s, T t) {
        BeanCopier.create(s.getClass(), t.getClass(), false).copy(s, t, null);

        return t;
    }


    public static <S, T> T copy(S s, Class<T> t) {
        T t1 = null;
        try {
            t1 = t.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        BeanCopier.create(s.getClass(), t, false).copy(s, t1, null);
        return t1;
    }

}
