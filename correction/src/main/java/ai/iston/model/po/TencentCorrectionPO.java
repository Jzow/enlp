package ai.iston.model.po;

import ai.iston.common.utils.BasePageQuery;
import ai.iston.model.bo.TencentScoreBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2022/01/12/14:37
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
public class TencentCorrectionPO extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer serialNumber;
    private Integer topicModule;
    private String moduleScore;
    private LocalDateTime correctionTime;
}
