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
package ai.james.utils.constants

/**
 * 斯坦福tokenzi标记 常量
 */
interface AnnotatorConstants {

    companion object {
        const val pos:          String  =   "tokenize,ssplit";

        const val lemma:        String  =   "tokenize,ssplit,pos,lemma";

        const val sentiment:    String  =   "tokenize,ssplit,pos,parse";

        const val ner:          String  =   "tokenize,ssplit,pos,lemma,ner,parse";

        const val parse:        String  =    "tokenize,ssplit,parse";

        const val depparse:     String  =    "tokenize,ssplit,pos";
    }

}