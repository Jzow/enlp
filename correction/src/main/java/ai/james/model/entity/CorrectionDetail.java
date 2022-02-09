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
package ai.james.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 批改详情Entity实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CorrectionDetail对象", description="批改详情Entity实体")
public class CorrectionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "详情id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "批改id")
    private String correctionId;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语 3:阅读 4:听力 5:翻译 6:语法结构")
    private Integer topicModule;

    @ApiModelProperty(value = "模块总分")
    private Integer moduleTotalScore;

    @ApiModelProperty(value = "模块得分")
    private Integer moduleScore;


}
