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

import lombok.Data;

/**
 * 词汇分类 Entity实体
 */
@Data
public class VocabClassification {

    private Integer id;
    private String word;
    private String type;
    private String classification;
}
