package ai.iston.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TencentCorrectionDTO对象", description="")
public class TencentCorrectionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语")
    private Integer topicModule;

    @ApiModelProperty(value = "答题卡集合")
    private List<Answer> answerList;

    @ApiModelProperty(value = "作业信息：答题时长/字数要求")
    private String homeworkInfo;

    @Data
    public static class Answer{
        @ApiModelProperty(value = "题干")
        private String questionTrunk;

        @ApiModelProperty(value = "问题")
        private String question;

        @ApiModelProperty(value = "写作：文本/ 口语：音频文件地址")
        private String file;
    }
}
