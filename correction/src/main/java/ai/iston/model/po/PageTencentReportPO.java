package ai.iston.model.po;

import ai.iston.common.utils.BasePageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2022/01/12/14:42
 * @Description:
 */
@Data
public class PageTencentReportPO extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer serialNumber;
    private Integer topicModule;
    private LocalDateTime correctionTime;
}
