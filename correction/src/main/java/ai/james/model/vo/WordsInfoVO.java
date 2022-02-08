package ai.james.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/06/19:15
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordsInfoVO {

    private String sentenceId;
    private String sentence;
    private List<WordsVO> wordList;
}
