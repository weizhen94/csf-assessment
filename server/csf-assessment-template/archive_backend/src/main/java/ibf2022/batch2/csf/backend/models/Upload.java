package ibf2022.batch2.csf.backend.models;

import org.springframework.web.multipart.MultipartFile;

public class Upload {

    private String name;
    private String title;
    private String comments;
    private MultipartFile file;

    public Upload(String name, String title, String comments, MultipartFile file) {
        this.name = name;
        this.title = title;
        this.comments = comments;
        this.file = file;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
