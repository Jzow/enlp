package james.ai.core;

import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 描述:
 *
 * @author James Zow
 * @create 2021/12/12
 */
public class GetNlpInfo {

    public static void main(String[] args) {
        Sentence sentence =  new Sentence("James so happy, because birthday");

        List<String> labelList = sentence.nerTags();
        String firstPosTag = sentence.posTag(0);
        labelList.stream().forEach(item -> {
            System.err.println(item);
        });
        System.out.println(firstPosTag);
    }
}
