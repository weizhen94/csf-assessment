package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Upload;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@RestController
@RequestMapping("/api")
public class UploadController {

	// TODO: Task 2, Task 3, Task 4

	@Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("name") String name, @RequestParam("title") String title, @RequestParam("comments") String comments, @RequestParam("file") MultipartFile file) {
    
	Upload upload = new Upload(name, title, comments, file);

    try {
            imageRepository.upload(upload);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload images to S3");
        }

        return ResponseEntity.ok("Upload successful");
    }



	// TODO: Task 5
	

	// TODO: Task 6

}
