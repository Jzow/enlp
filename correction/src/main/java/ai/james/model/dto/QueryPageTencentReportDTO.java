package ai.james.model.dto;

import ai.james.common.utils.BasePageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/19:56
 * @Description:
 */
@Data
@ApiModel(value="QueryPageTencentReportDTO", description="分页排序查询报告对象")
public class QueryPageTencentReportDTO extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private Integer serialNumber;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语 3:阅读 4:听力 5:翻译 6:语法结构")
    private Integer topicModule;

    @ApiModelProperty(value = "批改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime correctionTime;
}
