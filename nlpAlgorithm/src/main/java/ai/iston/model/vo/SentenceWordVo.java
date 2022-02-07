package ai.iston.model.vo;

import ai.iston.model.bo.WordBo;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
