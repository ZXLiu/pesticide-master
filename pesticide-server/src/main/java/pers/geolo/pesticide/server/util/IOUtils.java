package pers.geolo.pesticide.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

    public static void connectStream(InputStream inputStream, OutputStream outputStream,
                                     int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];//设置读写缓冲区
        int length;
        while ((length = inputStream.read(buffer)) != -1) {//将读出的数据写入outputStream中
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();//读写完毕关闭输入流
        outputStream.close();//读写完毕关闭输出流
    }

    public static void connectStream(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        connectStream(inputStream, outputStream, 1024);
    }
}
