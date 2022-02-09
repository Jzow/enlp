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
package ai.james.model.po;

import ai.james.common.utils.BasePageQuery;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2022/01/12/14:42
 * @Description:
 */
@Data
public class PageTencentReportPO extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer serialNumber;
    private Integer topicModule;
    private LocalDateTime correctionTime;
}
