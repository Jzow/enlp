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

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2021/12/28/18:00
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PubWorlds", description = "词汇表")
public class PubWorlds implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "单词")
    private String word;

    @ApiModelProperty(value = "词性（NOUN，V，ADJ，ADV）")
    private String wordType;

    @ApiModelProperty(value = "类别")
    private String wordCategory;

    @ApiModelProperty(value = "高职高专等级（初级，中级，高级）")
    private String wordHvcLevel;

    @ApiModelProperty(value = "欧标等级（a1a2: 初级，b1b2: 中级，c1c2：高级）")
    private String wordEsLevel;

    @ApiModelProperty(value = "iston等级（初级，中级，高级）")
    private String wordIstonLevel;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除,1已删,0未删")
    @TableLogic
    @JsonIgnore
    private Integer delFlag;
}
