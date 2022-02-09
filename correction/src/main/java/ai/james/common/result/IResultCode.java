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
package ai.james.common.result;

/**
 * @author pengmf
 * @since 2021/9/22
 */
public interface IResultCode {

    String getCode();

    String getMsg();

    /**
     * 获取业务码描述
     *
     * @return 业务码描述
     */
    default String getDesc() {
        return getMsg();
    }
}
