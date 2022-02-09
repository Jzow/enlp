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

import lombok.Data;

import java.util.List;

@Data
public class CorrectionDTO {

    // 作业Id
    private String homeworkId;

    // 班级Id
    private String classId;

    // 批改文章
    private List<StudentCorrectionDataDTO> answerList;

    @Data
    public static class StudentCorrectionDataDTO{

        // 学生答题卡（口语或者写作单道体 传入的是String字符串）
        private String studentAnswer;

        // 被批改的对象id（学生id）
        private String studentId;
    }
}
