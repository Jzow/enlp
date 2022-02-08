package ai.james.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: James Zow
 * @Date: 2021/12/28/18:00
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PubWorldsVo", description = "词汇表VO对象")
public class PubWorldsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "单词")
    private String word;

    @ApiModelProperty(value = "词性（NOUN，V，ADJ，ADV）")
    private String wordType;

    @ApiModelProperty(value = "类别")
    private String wordCategory;

    @ApiModelProperty(value = "欧标等级（a1a2: 初级，b1b2: 中级，c1c2：高级）")
    private String wordEsLevel;

    @ApiModelProperty(value = "iston高职等级（初级，中级，高级）")
    private String wordIstonLevel;

    @ApiModelProperty(value = "创建人")
    @JsonIgnore
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @JsonIgnore
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除,1已删,0未删")
    private Integer delFlag;
}
