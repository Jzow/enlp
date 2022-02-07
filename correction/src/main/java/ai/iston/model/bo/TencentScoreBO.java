package ai.iston.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/21:36
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
public class TencentScoreBO {

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语")
    private Integer topicModule;

    @ApiModelProperty(value = "得分 集合")
    private List<Integer> score;

    @ApiModelProperty(value = "总分")
    private Integer totalScore;
}
