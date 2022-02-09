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
 * @Date: 2022/01/30/10:53
 * @Description:
 */
@Data
public class GrammarInfoVo {

    private GrammarPerformance grammar_performance;

    @Data
    private static class GrammarPerformance{

        private GrammarLevel grammar_level;
        private GrammarDiversity grammar_diversity;
        private List<SentenceClassification> sentence_classification;

        @Data
        private static class GrammarLevel{
            private GrammarList grammar_list;
            private String result_level;

            @Data
            private static class GrammarList{
                private String name;
                private String percentage;
                private List<String> sentence;
            }
        }

        @Data
        private static class GrammarDiversity{
            private DiversityList diversity_list;
            private String result_diversity;

            @Data
            private static class DiversityList{
                private String name;
                private Integer number;
                private Integer type_number;
            }
        }

        @Data
        private static class SentenceClassification{
            private String name;
            private String percentage;
            private List<String> sentence;
        }
    }
}
