package ai.james.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordLevelVO {

    private String levelName;

    private Double percentage;

    private List<String> wordList;
}
