package com.smart119.testDemo;


import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOCopyDemo {
    public static void main(String[] args)  {
        nioCopyResourceFile();
    }


    public static void nioCopyResourceFile() {
        String srcPath = "/Users/wangguangyu/Downloads/1233.jpeg";
        String destPath = "/Users/wangguangyu/Downloads/12334444.jpeg";
        nioCopyFile(srcPath, destPath);

    }

    private static void nioCopyFile(String src, String destPath)  {
        // 输入流以及输入通道
        FileInputStream fileInputStream = null;
        FileChannel fileChannelIn = null;
        FileOutputStream outputStream = null;
        FileChannel fileChannelOut = null;
        try {
            File srcFile = new File(src);
            File destFile = new File(destPath);
            if (!destFile.exists()) {
                destFile.createNewFile();
                destFile.setReadable(true);
                destFile.setWritable(true);
                destFile.setExecutable(true);

            }
            // 输入流以及输入通道
            fileInputStream = new FileInputStream(srcFile);
            fileChannelIn = fileInputStream.getChannel();

            // 输出流以及输出通道
            outputStream = new FileOutputStream(destFile);
            fileChannelOut = outputStream.getChannel();
            // 读取的长度
            int length = -1;

            // 缓存
            ByteBuffer byteBuffer  =  ByteBuffer.allocate(1024);

            while((length = fileChannelIn.read(byteBuffer)) != -1) {
                // 第一次切换，字节缓存切换成读取模式
                byteBuffer.flip();
                // 将字节缓存输出到文件输出管理
                fileChannelOut.write(byteBuffer);
                // 第二次切换，清除buf, 变成写入模式
                byteBuffer.clear();
            }
            // 强制刷新到磁盘
            fileChannelOut.force(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 先关闭输出通道
            if (!ObjectUtils.isEmpty(fileChannelOut)) {
                try {
                    fileChannelOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 先关闭输出通道
            if (!ObjectUtils.isEmpty(outputStream)) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭文件输入通道
            if (!ObjectUtils.isEmpty(fileChannelIn)) {
                try {
                    fileChannelIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭文件输入流
            if (!ObjectUtils.isEmpty(fileInputStream)) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}



