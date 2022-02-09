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
package ai.james.utils

import java.util.*

/**
 * Properties组装工具
 */
class PropertiesFactory {

    var key     : String? = null;
    var value   : String? = null;

    companion object{
        fun setAnnotator(key: String, value: String): Properties {
            val properties = Properties();
            properties.setProperty(key, value);
            return properties;
        }
    }
}