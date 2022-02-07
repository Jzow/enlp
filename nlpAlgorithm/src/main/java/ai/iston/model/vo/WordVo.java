package ai.iston.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2022/01/18/21:31
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
public class WordVo {

    private String word;

    private String lemmaWord;

    private String wordType;
}
