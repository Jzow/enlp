package ai.james.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
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
@ApiModel(value="CorrectionModule对象", description="")
public class CorrectionModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "批改详情主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "题目(材料)类型 1:写作 2:口语 3:阅读 4:听力 5:翻译 6:语法结构")
    private Integer topicModule;

    @ApiModelProperty(value = "批改ID（主表关联）")
    private String correctionId;

    @ApiModelProperty(value = "报告标签1（写作：应用类，非应用类，应用类（书信邮件类）/ 口语：议论类 叙述类 朗读 中译英）")
    private String labelOne;

    @ApiModelProperty(value = "报告标签2 （议论类，叙述类，说明类，）")
    private String labelTwo;

    @ApiModelProperty(value = "模块后对应的json大文本（算法提供）")
    private String moduleDetailInfo;


}
