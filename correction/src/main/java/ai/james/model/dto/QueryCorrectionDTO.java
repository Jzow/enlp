package ai.james.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: James Zow
 * @Date: 2021/12/28/19:26
 * @Description:
 */
@Data
@ApiModel(value="QueryCorrectionDTO", description="查询词汇等级传输对象")
public class QueryCorrectionDTO {

    @ApiModelProperty(value = "作业Id")
    private String homeworkId;

    @ApiModelProperty(value = "班级Id")
    private String classId;

    @ApiModelProperty(value = "用户id（学生id）")
    private String userId;
}
