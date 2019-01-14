package com.github.tomdican.program.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIO {

    public static void main(String[] args) {
        String src = "file.text";
        String dist = "file2.text";
        fastCopy(src, dist);
    }

    public static void fastCopy(String src, String dist){

        try {
            /* 获得源文件的输入字节流 */
            FileInputStream fin = new FileInputStream(src);

            /* 获取输入字节流的文件通道 */
            FileChannel fcin = fin.getChannel();

            /* 获取目标文件的输出字节流 */
            FileOutputStream fout = new FileOutputStream(dist);

            /* 获取输出字节流的文件通道 */
            FileChannel fcout = fout.getChannel();

            /* 为缓冲区分配 1024 个字节 */
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            while (true) {
                /* 从输入通道中读取数据到缓冲区中 */
                int r = fcin.read(buffer);
                /* read() 返回 -1 表示 EOF */
                if (r == -1) {
                    break;
                }
                /* 切换读写 */
                buffer.flip();
                /* 把缓冲区的内容写入输出文件中 */
                fcout.write(buffer);
                /* 清空缓冲区 */
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
