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

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.AnnotatorPool;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.Properties;

/**
 * @Author: James Zow
 * @Date: 2022/01/04/14:03
 * @Description:
 */
public class CoreNLP extends AnnotationPipeline {

    public CoreNLP(){}

    public CoreNLP(Properties properties){}
}
