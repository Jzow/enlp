/**
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
package ai.james.core;

import edu.stanford.nlp.simple.Sentence;
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
        labelList.forEach(System.err::println);
        System.out.println(firstPosTag);
    }
}
