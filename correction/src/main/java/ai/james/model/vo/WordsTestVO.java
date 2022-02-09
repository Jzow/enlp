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

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 词汇测试VO对象
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "WordsTestVO", description = "词汇测试VO对象")
public class WordsTestVO {

    private Nlp nlp;
    private List<WordsInfoVO> wordsInfoVO;

    @Data
    public static class Nlp{
        private String studentId;
        private List<WordInfo> wordInfo;

        @Data
        public static class WordInfo{
            private String sentenceId;
            private String sentence;
            private List<WordList> wordList;

            @Data
            public static class WordList{
                private String wordType;
                private List<Words> words;

                @Data
                public static class Words{
                    private String originalWord;
                    private String lemmaWord;
                }
            }
        }
    }
}
