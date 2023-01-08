package pesticide.server.repository;

import java.io.IOException;

public interface FileRepository {

    FileInfo getFile(String id) throws IOException;//获取文件信息

    String saveFile(FileInfo fileInfo) throws IOException;//保存文件

    void removeFile(String id) throws IOException;//删除文件
}
