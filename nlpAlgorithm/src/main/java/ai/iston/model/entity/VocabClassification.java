package ai.iston.model.entity;

import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/13:52
 * @Description:
 */
@Data
public class VocabClassification {

    private Integer id;
    private String word;
    private String type;
    private String classification;
}
