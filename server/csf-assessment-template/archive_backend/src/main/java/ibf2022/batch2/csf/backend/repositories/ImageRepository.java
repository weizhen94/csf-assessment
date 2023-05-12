package ibf2022.batch2.csf.backend.repositories;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import ibf2022.batch2.csf.backend.models.Upload;

@Repository
public class ImageRepository {

	@Autowired
    private AmazonS3 s3Client;

    @Value("${DO_STORAGE_BUCKETNAME}")
    private String bucketName;

	//TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
	public List<String> upload(Upload upload) throws IOException {
		List<String> urls = new ArrayList<>();
	
		ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(upload.getFile().getBytes()));
		ZipEntry zipEntry = zis.getNextEntry();
		while (zipEntry != null) {
			String fileName = zipEntry.getName();
			File tempFile = File.createTempFile(fileName, "");
			try (FileOutputStream fos = new FileOutputStream(tempFile)) {
				byte[] buffer = new byte[1024];
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			}
	
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(determineContentType(fileName));
			s3Client.putObject(bucketName, fileName, new FileInputStream(tempFile), metadata);
	
			// Add the URL of the uploaded image to the list
			String imageUrl = s3Client.getUrl(bucketName, fileName).toString();
			urls.add(imageUrl);
	
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	
		return urls;
	}
	

    private String determineContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            default:
                return "application/octet-stream";
        }
    }
}

