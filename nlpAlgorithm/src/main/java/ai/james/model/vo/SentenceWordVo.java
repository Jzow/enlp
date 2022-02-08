package ai.james.model.vo;

import ai.james.model.bo.WordBo;
import lombok.Data;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/18/21:35
 * @Description:
 */
@Data
public class SentenceWordVo {

    private Integer id;

    private String sentence;

    private List<WordBo> wordList;
}
