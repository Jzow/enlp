package ai.james;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 *
 * @author James Zow
 * @create 2022/1/15
 */
@Builder
@AllArgsConstructor
public class Document {

    private StanfordCoreNLP stanfordCoreNLP;

    private CoreDocument coreDocument;
}
