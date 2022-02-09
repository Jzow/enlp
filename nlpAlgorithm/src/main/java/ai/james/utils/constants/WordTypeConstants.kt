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
 * 词汇POS类型 常量
 */
interface WordTypeConstants {

    companion object {
        val NNWord          = listOf("NN","NNS","NNP","NNPS")

        val VVWord          = listOf("VB","VBD","VBG","VBN","VBP","VBZ")

        val ADJWod          = listOf("JJ","JJR","JJS")

        val ADVWord         = listOf("RB","RBR","RBS")

        const val NOUNType  = "NOUN";

        const val VERBType  = "VERB";

        const val ADJType   = "ADJ";

        const val ADVType   = "ADV";
    }
}