package ai.iston.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/0:52
 * @Description:
 */
@Data
public class WordsTestBO {

    private String sentenceId;

    private String sentence;

    private List<WordList> wordList;

    @Data
    public static class WordList{
        private List<Words> words;
        private String wordType;

        @Data
        public static class Words{
            private String originalWord;
            private String lemmaWord;
        }
    }
}
