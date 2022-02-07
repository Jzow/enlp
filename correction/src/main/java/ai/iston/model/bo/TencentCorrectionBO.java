package ai.iston.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2022/01/12/13:57
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
public class TencentCorrectionBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "序号")
    private Integer serialNumber;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语")
    private Integer topicModule;

    @ApiModelProperty(value = "模块得分（JSON）")
    private String moduleScore;

    @ApiModelProperty(value = "批改时间")
    private LocalDateTime correctionTime;

    @ApiModelProperty(value = "分数封装")
    private TencentScoreBO tencentScore;
}
