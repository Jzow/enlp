package ai.james.model.po;

import ai.james.common.utils.BasePageQuery;
import lombok.Data;

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
