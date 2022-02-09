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
package ai.james.service;

import ai.james.model.entity.Article;
import ai.james.model.vo.GrammarInfoVo;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/26/19:50
 * @Description:
 */
public interface GrammarPerformanceService {

    GrammarInfoVo getGrammarInfo(List<Article> articleList);
}
