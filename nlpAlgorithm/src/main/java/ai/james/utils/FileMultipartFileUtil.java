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
package ai.james.utils;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * @Author: James Zow
 * @Date: 2022/01/14/10:48
 * @Description: 文件转换工具
 */
public class FileMultipartFileUtil {

    /**
     * file -> MultipartFile
     * @param file File Type
     * @return File MultipartFile
     */
    public static MultipartFile fileToMultipartFile(File file) {

        try {
            FileInputStream inputStream = new FileInputStream(file);
            return new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.MULTIPART_FORM_DATA.toString(), inputStream);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MultipartFile -> File
     *
     * @param file MultipartFile Type
     */
    public static File multipartFileToFile(MultipartFile file) {
        File toFile = null;
        try {
            if (file.getSize() <= 0) {
                file = null;
            } else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
                inputStreamToFile(ins, toFile);
                ins.close();
            }
            return toFile;
        }catch (Exception e){
            e.printStackTrace();
        }
        return toFile;
    }

    /**
     * 获取流文件
     * @param ins Stream流
     * @param file file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
