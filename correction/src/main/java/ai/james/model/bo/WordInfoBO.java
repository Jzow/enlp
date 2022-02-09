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
package ai.james.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/05/15:53
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordInfoBO {

    private String sentenceId;

    private String sentence;

    private List<WordsBO> wordList;

}