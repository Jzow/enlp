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

import lombok.Data;

import java.util.List;

/**
 * 词汇Bo
 */
@Data
public class WordsBO {

    private List<Words> words;

    private String wordType;

    @Data
    public static class Words{
        private String originalWord;
        private String lemmaWord;
    }
}
