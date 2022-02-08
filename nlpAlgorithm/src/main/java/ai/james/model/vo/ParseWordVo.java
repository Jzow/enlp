package ai.james.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/24/13:51
 * @Description:
 */
@Data
public class ParseWordVo {

    private String type;

    private List<String> words;
}
