/**
 * Project: ams-abd-ast
 * File created at 2020/2/9 14:36
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.transaction;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Base64;

/**
 * 功能描述
 * 文件编码解码工具类
 *
 * @author linlipeng
 * @version 1.0
 * @type FileHandleUtils
 * @date 2020/2/9 14:36
 */
public class FileHandleUtils {

    public static String encodeFile(File file) throws IOException {
        byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
        return org.apache.commons.codec.binary.Base64.encodeBase64String(readFileToByteArray);
    }

    public static String encodeFile(String filePath) throws IOException {
        return encodeFile(new File(filePath));
    }

    public static void decodeFile(String codes, File file) throws IOException {
        byte[] decodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(codes);
        FileUtils.writeByteArrayToFile(file, decodeBase64);
    }

    public static void decodeFile(String codes, String filePath) throws IOException {
        decodeFile(codes, new File(filePath));
    }

    //将Byte数组转换成文件
    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        // apach 工具类
        String encodeStr = encodeFile("E:\\OCBC\\test.pdf");
        decodeFile(encodeStr, "E:\\OCBC\\test2.pdf");

//        String string = "密";
//        System.out.println("old string: " + string);
//        String base64 = Base64.getEncoder().encodeToString(string.getBytes("UTF-8"));
//        System.out.println("base64 decode: " + base64);
//        byte[] bytes = Base64.getDecoder().decode(base64);
//        System.out.println("base64 encode: " + new String(bytes, "UTF-8"));

        // java 原生api
        byte[] bytes = Base64.getDecoder().decode(encodeStr);
        getFileByBytes(bytes, "E:\\OCBC", "test3.pdf");
    }
}
