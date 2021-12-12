package james.ai.core;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

/**
 * /**
 * 描述:
 *
 * @author James Zow
 * @create 2021/12/12
 */
public class SimpleCoreNLPDemo {

    public static void main(String[] args) {
        Document doc = new Document("I know the answer.\n" +
                "My parents are rich.\n" +
                "He works in a bank.\n" +
                "There is a supermarket in this street.\n" +
                "I am eating.\n" +
                "We are working on a new project.\n" +
                "He is studying now.\n" +
                "They have gone to the cinema.\n" +
                "She has arrived home.\n" +
                "I have been wating for you.\n" +
                "She have been learning Spanish for six months.\n" +
                "I was in New York last week.\n" +
                "We went on holiday to China last year.\n" +
                "I was driving home from work.\n" +
                "They were watching the news.\n" +
                "They had listened to his music.\n" +
                "She had been studying for ages.\n" +
                "The cinema will close in November.\n" +
                "I am going to leave.\n" +
                "They are going to come.\n" +
                "She is going to buy a car.\n" +
                "We spend three days in Rome.\n" +
                "The train arrives at 10 pm tonight.\n" +
                "I am meeting Gavin at the club tonight.\n" +
                "We are visiting friends at the weekend.\n" +
                "Jack is playing soccer tomorrow.\n" +
                "I am to go on with the work\n" +
                "The Student Games are to take place in Melbourne next year.\n" +
                "The Queen is to visit Portugal in November.\n" +
                "I am about to go to Harvard University.\n" +
                "He is due to start a new project.\n" +
                "They are about to leave.\n" +
                "I will be working.\n" +
                "I am going to be studying all night.\n" +
                "My wife is going to be performing.\n" +
                "Two politicians are going to be talking about crime.\n" +
                "I will have finished this book soon.\n" +
                "I will have been studying here for three months.\n" +
                "She would be successful.\n" +
                "I was going to be late.\n" +
                "We were going to be buy a new dog.\n" +
                "I would be doing my homework all day long.\n" +
                "She would have completed her assignment.\n" +
                "I should have been working here for two hours by that time.\n");

        for(Sentence sentence : doc.sentences()){
            System.out.println(sentence.word(1));

            System.out.println(sentence.lemma(2));


            System.out.println(sentence.parse());

        }
    }
}
