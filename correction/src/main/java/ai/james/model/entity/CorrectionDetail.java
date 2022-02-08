package ai.james.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CorrectionDetail对象", description="")
public class CorrectionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "详情id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "批改id")
    private String correctionId;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语 3:阅读 4:听力 5:翻译 6:语法结构")
    private Integer topicModule;

    @ApiModelProperty(value = "模块总分")
    private Integer moduleTotalScore;

    @ApiModelProperty(value = "模块得分")
    private Integer moduleScore;


}
