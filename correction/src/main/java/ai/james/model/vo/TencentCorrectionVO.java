package ai.james.model.vo;

import ai.james.model.bo.TencentScoreBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/19:48
 * @Description:
 */
@Data
public class TencentCorrectionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "序号")
    private Integer serialNumber;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语")
    private Integer topicModule;

    @JsonIgnore
    @ApiModelProperty(value = "模块得分（JSON）")
    private String moduleScore;

    @ApiModelProperty(value = "模块得分（JSON）")
    private TencentScoreBO score;

    @ApiModelProperty(value = "批改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime correctionTime;
}
