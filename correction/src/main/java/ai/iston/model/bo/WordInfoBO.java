package ai.iston.model.bo;

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
