package ai.iston.model.bo;

import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/13:54
 * @Description:
 */
@Data
public class VocabClassificationBo {

    private String word;
    private String type;
    private String classification;
}
