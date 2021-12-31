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
package ai.james

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.jvm.Throws

class FileUtils {

   @Throws(Exception::class)
   fun readTxtFile(filePath: String, fileName: String) : String?{
      var result: String? = ""
      var fileReader: FileReader? = null
      var bufferReader: BufferedReader? = null
      try {
         fileReader = FileReader(File("$filePath/$fileName"))
         bufferReader = BufferedReader(fileReader)

         var read: String? = null
         while (run {
                    read = bufferReader.readLine()
                    read
                 } != null) {
            result = result + read + "\r\n"
         }
      }catch (e : Exception){
         e.printStackTrace();
      }finally {
         bufferReader?.close()
         fileReader?.close()
      }
      return result;
   }
}