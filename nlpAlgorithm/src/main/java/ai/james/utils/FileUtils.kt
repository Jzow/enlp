package ai.james.utils

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