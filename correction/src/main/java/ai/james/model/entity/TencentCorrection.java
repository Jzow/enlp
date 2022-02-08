package ai.james.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
public class TencentCorrection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "序号")
    private Integer serialNumber;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语")
    private Integer topicModule;

    @ApiModelProperty(value = "模块得分（JSON）")
    private String moduleScore;

    @ApiModelProperty(value = "批改时间")
    private LocalDateTime correctionTime;

}
