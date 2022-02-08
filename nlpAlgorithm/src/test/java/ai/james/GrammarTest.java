package ai.james;

import ai.james.utils.PropertiesFactory;
import ai.james.utils.constants.AnnotatorConstants;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;

import java.util.Set;

/**
 * @Author: James Zow
 * @Date: 2022/01/26/19:54
 * @Description:
 */
public class GrammarTest {

    public static void main(String[] args) throws Exception {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesFactory.Companion.setAnnotator("annotators", AnnotatorConstants.ner));

        String data = "Some people consider that the loss of individual species of plants and animals is the main environment problem of our time. Attempts to preserve rare or dying species are carried out in zoos or other specialist plant and animal facilities, through breeding programs, preservation orders and other forms of protection and there was genuine sadness among many when the last surviving white rhino died earlier this year.\n" +
                "Survival of the fittest is nature’s way of ruthlessly allowing weaker specimens to die out, although mankind has also contributed to falling animal numbers through hunting and poaching. However, whether saving individual species should be the main focus of environmental protection is debatable. Climate change and excessive use of plastic are just two other major problem that deserve our attention. \n" +
                "The evidence of climate change is there for us all to see: rising sea levels and associated flooding with the loss of homes at low land levels are becoming more frequent. Many countries and island communities are now calling for urgent action to delay, halt or even reverse the process, although if we believe the experts, reversal is impossible. Habitable areas are gradually shrinking, often for some of the poorest groups of people, but there seems to be little obvious action taking place. If the major contributors to climate change will not support the reduction of greenhouse gases, there is little hope for the future.\n" +
                "Naturalists have also begun to expose the serious problems arising form discarded plastic waste. Birds and animals can die gruesome deaths through being trapped or caught up in plastic bags or ropes, while the long process required for these plastics to decay means that they remain a threat for many years to come. Worse, there is now beginning to be evidence of sea creatures ingesting smaller globules of disintegrating  plastic, which harms them and all the other creatures in their particular food chain.\n" +
                "The loss of particular species, both flora and fauna, is cause for regret, but I do not agree that they are the most important environmental problems these days. If climate change and damage form plastic continue, there will be less land and less food for everyone, including plants and animals.\n";

        String data2 = "The evidence of climate change is there for us all to see: rising sea levels and associated flooding with the loss of homes at low land levels are becoming more frequent. If the major contributors to climate change will not support the reduction of greenhouse gases, there is little hope for the future.";

        String data3 = "I hurt learning that is hard for me";


        String data4 = "Attempts to preserve rare or dying species are carried out in zoos or other specialist plant and animal facilities, " +
                "through breeding programs, preservation orders and other forms of protection and there was genuine sadness among many" +
                " when the last surviving white rhino died earlier this year.";

        CoreDocument document = pipeline.processToCoreDocument(data4);

        // display tokens

        for (CoreSentence sentence : document.sentences()) {
            System.out.println("句子：" + sentence.text() + "\r");
            System.out.println(sentence.posTags());

            for (CoreLabel tok : sentence.tokens()) {

                if(tok.tag().contains("VBN") || tok.tag().contains("VBG")){
                    System.out.println("非谓语:" + tok.word());
                }else if(tok.tag().contains("VB") || tok.tag().contains("VBD")|| tok.tag().contains("VBP") || tok.tag().contains("VBZ")){
                    System.out.println("谓语:" + tok.word());
                }
            }
//            SemanticGraph semanticGraph = sentence.dependencyParse();
//            System.out.println("First Root：" + semanticGraph.getFirstRoot() +"\r");
//
//            System.out.println("dependency Parse typed：" + sentence.dependencyParse().typedDependencies() +"\r");
//            System.out.println("dependency Parse：" + sentence.dependencyParse() +"\r");
           System.out.println("dependency Parse List：" + sentence.dependencyParse().toList() +"\r");
//
//            System.out.println("constituency Parse：" + sentence.constituencyParse()+"\r");
         //   System.out.println("constituency Parse List：" + Arrays.toString(sentence.constituencyParse().children()) +"\r");

            Tree tree = sentence.constituencyParse();
        //    System.out.println("树结构：" + tree + "\r");

            Set<Constituent> treeConstituents = sentence.constituencyParse().constituents(new LabeledScoredConstituentFactory());
       //     System.out.println(treeConstituents.size());
            for (Constituent constituent : treeConstituents) {
               if (constituent.label() != null && constituent.label().toString().equals("SBAR")) {
                    System.err.println("成分: "+constituent);
                   System.err.println(tree.getLeaves().subList(constituent.start(), constituent.end()+1));
                }
            }
        }

    }
}
