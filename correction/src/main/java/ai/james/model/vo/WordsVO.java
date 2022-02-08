package ai.james.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: James Zow
 * @Date: 2022/01/06/19:16
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordsVO {
    private String word;
    private String wordType;
    private String levelName;
}
