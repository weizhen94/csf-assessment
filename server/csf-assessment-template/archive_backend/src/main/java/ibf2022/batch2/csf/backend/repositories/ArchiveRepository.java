package ibf2022.batch2.csf.backend.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository {

	@Autowired
    private MongoTemplate mongoTemplate;

	//TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	/*
	db.archives.insert({
    "bundleId": "<bundleId>",
    "date": "<uploadDate>",
    "title": "<title>",
    "name": "<name>",
    "comments": "<comments>",
    "urls": ["<url1>", "<url2>", ..., "<urlN>"]
    })
	 */
	public String recordBundle(String title, String name, String comments, List<String> urls) {
        String bundleId = UUID.randomUUID().toString().substring(0, 8);
        Date uploadDate = new Date();

        Document document = new Document();
        document.put("bundleId", bundleId);
        document.put("date", uploadDate);
        document.put("title", title);
        document.put("name", name);
        document.put("comments", comments);
        document.put("urls", urls);

        mongoTemplate.insert(document, "archives");

        return bundleId;
    }


	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundleByBundleId(/* any number of parameters here */) {
		return null;
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}


}
