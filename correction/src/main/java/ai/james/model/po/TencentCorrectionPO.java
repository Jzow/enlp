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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 腾讯批改Po
 */
@Data
@Builder
@AllArgsConstructor
public class TencentCorrectionPO extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer serialNumber;
    private Integer topicModule;
    private String moduleScore;
    private LocalDateTime correctionTime;
}
