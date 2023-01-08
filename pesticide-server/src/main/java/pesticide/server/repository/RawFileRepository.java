package pesticide.server.repository;

import pesticide.server.exception.ConfigurationException;
import pesticide.server.exception.FileNotFoundException;
import pesticide.server.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.UUID;

@Component
public class RawFileRepository implements FileRepository {

    @Value("${fileUpdatePath}")
    private String fileUpdatePath = null;

    private String makeFilePath(String fileType) {//生成文件路径
        StringBuilder fileNameBuilder = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        fileNameBuilder.append(calendar.get(Calendar.YEAR))//根据当前日期生成文件路径
                .append(calendar.get(Calendar.MONTH) + 1)
                .append("/" + calendar.get(Calendar.DATE))
                .append("/")
                .append(UUID.randomUUID())
                .append(".")
                .append(fileType);
        return fileNameBuilder.toString();//返回生成的文件路径
    }

    private String getSavePath(String id) {//获取文件更新路径
        if (fileUpdatePath == null) {//文件更新路径为空
            throw new ConfigurationException("can not find properties 'fileUpdatePath' in configuration file");
        }
        return fileUpdatePath + id;
    }

    @Override
    public FileInfo getFile(String id) throws IOException {//获取文件信息
        File file = new File(getSavePath(id));
        if (!file.exists()) {//文件不存在
            throw new pesticide.server.exception.FileNotFoundException("the file of path = " + id + " is not found.");
        }
        return new FileInfo() {//返回文件信息
            @Override
            public String getId() {
                return id;
            }//获取文件Id

            @Override
            public String getFilename() {
                return file.getName();
            }//获取文件名

            @Override
            public String getContentType() {// 获取指定文件名的MIME类型
                FileNameMap fileNameMap = URLConnection.getFileNameMap();
                String contentType = fileNameMap.getContentTypeFor(file.getPath());
                return contentType;
            }

            @Override
            public long getLength() {
                return file.length();
            }//获取文件长度

            @Override
            public InputStream getInputStream() throws IOException {//获取文件输入流
                return new FileInputStream(file);
            }
        };
    }

    @Override
    public String saveFile(FileInfo fileInfo) throws IOException {//保存文件
        String filename = fileInfo.getFilename();//获取文件名
        String fileType = filename.substring(filename.lastIndexOf(".") + 1);//获取文件类型
        String id = makeFilePath(fileType);//生成文件路径
        File file = new File(getSavePath(id));
        if (!file.getParentFile().exists()) {//该文件的父文件夹不存在
            boolean success = file.getParentFile().mkdirs();//创建父文件夹保存当前文件
            if (!success) {//创建不成功
                throw new IOException("can not make directory to save file:" + file.getPath());
            }
        }
        if (!file.exists()) {//当前文件不存在
            boolean success = file.createNewFile();//创建当前文件
            if (!success) {//创建不成功
                throw new IOException("can not create file:" + file.getPath());
            }
        }
        IOUtils.connectStream(fileInfo.getInputStream(), new FileOutputStream(file));//将获取到的文件信息写入当前文件
        return id;//返回当前文件路径
    }

    @Override
    public void removeFile(String id) throws IOException {//删除文件
        File file = new File(getSavePath(id));//根据路径获取文件
        if (!file.exists()) {//文件不存在
            throw new FileNotFoundException("the file of path = " + id + " is not found.");
        }
        boolean success = file.delete();//删除文件
        if (!success) {//删除不成功
            throw new IOException("can not delete file:" + file.getPath());
        }
    }
}
