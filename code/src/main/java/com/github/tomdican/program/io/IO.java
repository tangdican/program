package com.github.tomdican.program.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class IO {

    public static void main(String[] args) {
        String src = "file.text";
        String dist = "file2.text";
        //copyFile(src, dist);
        readFileContent(src);
    }

    public static void copyFile(String src, String dist) {
        try {

            FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(dist);

            byte[] buffer = new byte[20 * 1024];
            int cnt;

            // read() 最多读取 buffer.length 个字节
            // 返回的是实际读取的个数
            // 返回 -1 的时候表示读到 eof，即文件尾
            while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, cnt);
            }
            in.close();
            out.close();

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    public static void readFileContent(String filePath) {

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
            // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
            // 因此只要一个 close() 调用即可
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
