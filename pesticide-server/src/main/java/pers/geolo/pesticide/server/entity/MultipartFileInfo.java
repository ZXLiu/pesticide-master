package pers.geolo.pesticide.server.entity;

import pers.geolo.pesticide.server.repository.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class MultipartFileInfo implements FileInfo {//上传文件类，用于上传用户头像

    private MultipartFile multipartFile;

    public MultipartFileInfo(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getFilename() {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public String getContentType() {
        return multipartFile.getContentType();
    }

    @Override
    public long getLength() {
        return multipartFile.getSize();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return multipartFile.getInputStream();
    }
}
