package ai.iston.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2022/01/21/21:08
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
public class WordBo {

    private String word;

    private String lemmaWord;

    private String wordType;
}
