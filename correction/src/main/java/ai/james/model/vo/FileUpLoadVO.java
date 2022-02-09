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
package ai.james.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传返回数据对象
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileUpLoadVO", description = "文件上传返回数据对象")
public class FileUpLoadVO {

    @JsonIgnore
    private MultipartFile multipartFile;

    @ApiModelProperty("文件访问地址")
    private String url;
    @ApiModelProperty("文件加密")
    private String sha1;
    @ApiModelProperty("文件路径")
    private String path;
    private String domain;
    private String scene;
    @ApiModelProperty("文件大小")
    private Long size;
    private Long mtime;
    private String scenes;
    @ApiModelProperty("文件上传响应消息体")
    private String retmsg;
    @ApiModelProperty("文件上传响应码")
    private Long retcode;
    @ApiModelProperty("文件src路径")
    private String src;
    @ApiModelProperty("文件名称")
    private String fileName;
}
