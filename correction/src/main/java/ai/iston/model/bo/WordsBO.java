package ai.iston.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2021/12/28/21:29
 * @Description:
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
