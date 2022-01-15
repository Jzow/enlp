/*
 * MIT License
 *
 * Copyright (c) 2021 James Zow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software
 */
package ai.james;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

public class SentenceTest {

    public static void main(String[] args) throws Exception {

        FileUtils fileUtils = new FileUtils();
        String data = fileUtils.readTxtFile("../swordcome/word/src/main/resources", "test-data-2.txt");

        // sentParse(data);
        sentLemma(data);
    }

    public static void sentParse(String data){
        Document doc = new Document(data);
        for (Sentence sent : doc.sentences()){
            // 获取每段句子第二个单词
            System.out.println(sent.word(1));
            // 每段句子的 选区解析
            System.out.println(sent.parse());
        }
    }

    public static void sentLemma(String data){
        Document doc = new Document(data);
        for (Sentence sent : doc.sentences()){
            // 句子还原
            System.out.println(sent.lemmas());
        }
    }
}
