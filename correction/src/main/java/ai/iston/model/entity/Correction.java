package ai.iston.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 全自动批改表
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
@ApiModel(value="Correction对象", description="全自动批改表")
public class Correction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全自动批改 主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "作业id")
    private String homeworkId;

    @ApiModelProperty(value = "班级id")
    private String classId;

    @ApiModelProperty(value = "用户id（学生id）")
    private String userId;

    @ApiModelProperty(value = "尝试重新批改次数")
    private Integer tryCount;

    @ApiModelProperty(value = "批改状态（0-成功，1-失败）")
    private Integer status;

    @ApiModelProperty(value = "交作业方式（1-平台，2-本地）")
    private Integer submitMethod;


}
