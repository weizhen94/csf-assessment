package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Upload;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@RestController
@RequestMapping("/api")
public class UploadController {

	// TODO: Task 2, Task 3, Task 4

	@Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ArchiveRepository archiveRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("name") String name, @RequestParam("title") String title, @RequestParam("comments") String comments, @RequestParam("file") MultipartFile file) {
    
    Upload upload = new Upload(name, title, comments, file);
    
    List<String> urls = new ArrayList<>(); // This should contain URLs of uploaded images to S3

    try {
            urls = imageRepository.upload(upload);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Failed to upload images to S3\"}");
        }

        try {
            String bundleId = archiveRepository.recordBundle(title, name, comments, urls);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"bundleId\": \"" + bundleId + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Failed to record the bundle\"}");
        }
    }





	// TODO: Task 5
	

	// TODO: Task 6

}
