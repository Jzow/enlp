package ai.iston.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/21/21:06
 * @Description:
 */
@Data
public class ParseWordBo {

    private String type;

    private List<String> words;
}
