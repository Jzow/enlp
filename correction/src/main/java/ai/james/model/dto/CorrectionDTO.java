package ai.james.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CorrectionDTO {

    // 作业Id
    private String homeworkId;

    // 班级Id
    private String classId;

    // 批改文章
    private List<StudentCorrectionDataDTO> answerList;

    @Data
    public static class StudentCorrectionDataDTO{

        // 学生答题卡（口语或者写作单道体 传入的是String字符串）
        private String studentAnswer;

        // 被批改的对象id（学生id）
        private String studentId;
    }
}
