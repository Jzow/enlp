package ai.iston.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TencentCorrectionDetail对象", description="")
public class TencentCorrectionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "题型，只有口语有（自我介绍，描述/解释，议论）")
    private String questionType;

    @ApiModelProperty(value = "腾讯批改主表id")
    private String tencentCorrectionId;

    @ApiModelProperty(value = "作业信息（写作就是-字数数字比如200/口语就是分钟 2min 30s）")
    private String homeworkInfo;

    @ApiModelProperty(value = "题干")
    private String questionTrunk;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "批改详情（JSON）算法数据")
    private String moduleDetailInfo;

    @ApiModelProperty(value = "批改时间")
    private LocalDateTime correctionTime;

}
