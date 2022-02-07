package ai.iston.model.vo;

import ai.iston.model.bo.WordsTestBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/06/19:16
 * @Description:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
