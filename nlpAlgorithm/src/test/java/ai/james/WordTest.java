package ai.james;

import ai.james.model.vo.WordVo;
import ai.james.utils.FileUtils;
import ai.james.utils.PropertiesFactory;
import ai.james.utils.constants.AnnotatorConstants;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: James Zow
 * @Date: 2022/01/19/11:17
 * @Description:
 */
public class WordTest {

    public static void main(String[] args) throws Exception {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.lemma));

        FileUtils fileUtils = new FileUtils();
        String data = fileUtils.readTxtFile("../swordcome/word/src/main/resources", "test-data-1.txt");

        CoreDocument document = pipeline.processToCoreDocument(data);

        List<WordVo> words = new ArrayList<>();
        for (CoreLabel tok : document.tokens()) {
            WordVo word = WordVo.builder()
                    .word(tok.word())
                    .lemmaWord(tok.lemma())
                    .wordType(tok.tag())
                    .build();
            words.add(word);
        }

        for (WordVo word : words) {
            System.out.println("原词：" + word.getWord() + "还原词: "+ word.getLemmaWord() + "————词性：" + word.getWordType());
        }
    }
}
