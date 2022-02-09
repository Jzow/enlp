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
 * @Author: James Zow
 * @Date: 2022/01/25/13:47
 * @Description:
 */
@Data
public class VocabLevel {

    private Integer id;

    private String word;

    private String type;

    private String level;

    private Integer levelId;
}
