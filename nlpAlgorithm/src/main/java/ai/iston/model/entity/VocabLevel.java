package ai.iston.model.entity;

import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/13:47
 * @Description:
 */
@Data
public class VocabLevel {

    private Integer id;

    private String word;

    private String type;

    private String level;

    private Integer levelId;
}
