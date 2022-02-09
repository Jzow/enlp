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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pengmf
 * @since 2021/9/22
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    /* 成功 */
    SUCCESS("200", "成功"),

    /* 默认失败 */
    COMMON_FAIL("500", "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID("1001", "参数无效"),
    PARAM_IS_BLANK("1002", "参数为空"),
    PARAM_TYPE_ERROR("1003", "参数类型错误"),
    PARAM_NOT_COMPLETE("1004", "参数缺失"),


    /* 用户错误 */
    USER_NOT_LOGIN("2000", "用户未登录"),
    USER_ACCOUNT_EXPIRED("2000", "账号已过期"),
    USER_CREDENTIALS_ERROR("2000", "用户名或密码错误"),
    USER_CREDENTIALS_EXPIRED("2000", "密码过期"),
    USER_ACCOUNT_DISABLE("2000", "账号不可用"),
    USER_ACCOUNT_LOCKED("2000", "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST("2000", "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST("2000", "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS("2000", "账号下线"),
    VERIFICATION_CODE_ERROR("2000", "验证码错误"),
    VERIFICATION_CODE_EXPIRED("2000", "验证码过期"),
    TOKEN_INVALID("2000", "token无效"),
    ACCOUNT_IS_SQUEEZED("2000", "账号被挤"),

    /* 业务错误 */
    NO_PERMISSION("3001", "暂无权限"),
    SYSTEM_EXECUTION_ERROR("3002", "系统内部错误"),
    NO_DATA_FOUND("4004","没有查询到对应的数据"),

    CORRECTION_DATA_EXIST("3003","该班级学生作业已批改过，不能重复批改");


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private String code;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }


    public static ResultCode getValue(String code) {
        for (ResultCode value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_EXECUTION_ERROR; // 默认系统执行错误
    }
}
