package pesticide.server.repository;

import java.io.IOException;
import java.io.InputStream;

public interface FileInfo {

    String getId();//获取文件Id

    String getFilename();//获取文件名

    String getContentType();//获取文件的MIME类型

    long getLength();//获取文件长度

    InputStream getInputStream() throws IOException;//获取文件输入流
}
