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

import lombok.Data;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/24/22:51
 * @Description:
 */
@Data
public class WordInfoVo {

    private WordLevel word_level;
    private String word_diversity;
    private List<WordClassification> word_classification;

    @Data
    private static class WordLevel{
        private List<WordList> word_list;
        private String result_level;

        @Data
        private static class WordList {
            private String level_name;
            private String percentage;
            private List<String> lemma_words;
        }
    }

    @Data
    private static class WordClassification{
        private String name;
        private List<WordList> word_list;

        @Data
        private static class WordList {
            private String label;
            private List<String> words;
            private String percentage;
        }
    }
}
