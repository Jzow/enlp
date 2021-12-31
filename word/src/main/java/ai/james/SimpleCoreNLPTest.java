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

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class SimpleCoreNLPTest {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

        CoreDocument document = pipeline.processToCoreDocument("data");

        List<String> words = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        for (CoreLabel tok : document.tokens()) {
            words.add(tok.word());
            tags.add(tok.tag());
        }

        for (String word : words) {
            System.out.println(word);
        }
        for (String tag : tags) {
            System.out.println(tag);
        }
    }
}
