package io.viff.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name = "file")
public class FileModel extends BaseModel {

    @Column(name = "file_name",length = 1024)
    private String fileName;

    @Column(name = "file_path", length = 2048)
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
