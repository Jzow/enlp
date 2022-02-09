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
package ai.james.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2021/12/28/19:26
 * @Description:
 */
@Data
@ApiModel(value="QueryCorrectionDTO", description="查询词汇等级传输对象")
public class QueryCorrectionDTO {

    @ApiModelProperty(value = "作业Id")
    private String homeworkId;

    @ApiModelProperty(value = "班级Id")
    private String classId;

    @ApiModelProperty(value = "用户id（学生id）")
    private String userId;
}
