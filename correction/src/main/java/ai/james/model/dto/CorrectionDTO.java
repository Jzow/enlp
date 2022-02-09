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

import java.util.List;

/**
 * 批改Dto
 */
@Data
@ApiModel(value="QueryCorrectionDTO", description="批改Dto")
public class CorrectionDTO {

    @ApiModelProperty(value = "作业Id")
    private String homeworkId;

    @ApiModelProperty(value = "班级Id")
    private String classId;

    @ApiModelProperty(value = "批改文章")
    private List<StudentCorrectionDataDTO> answerList;

    @Data
    public static class StudentCorrectionDataDTO{

        @ApiModelProperty(value = "学生答题卡（口语或者写作单道体 传入的是String字符串）")
        private String studentAnswer;

        @ApiModelProperty(value = "被批改的对象id（学生id）")
        private String studentId;
    }
}
