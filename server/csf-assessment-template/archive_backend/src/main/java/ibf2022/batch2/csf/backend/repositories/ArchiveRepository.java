package ibf2022.batch2.csf.backend.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	/*
	db.archives.find({
    "bundleId" : "bundleId"
    })
	 */
	public Object getBundleByBundleId(String bundleId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("bundleId").is(bundleId));
		return mongoTemplate.findOne(query, Object.class, "archives");
	}
	

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	/*
	db.archives.aggregate([
	{
    $project: {
      title: 1,
      date: 1,
    },
  },
  {
    $sort: {
      date: -1,
      title: 1,
    },
  },
]);
	 */
	public List<Document> getBundles() {
		ProjectionOperation projectionOperation = Aggregation.project("title", "date");
		Aggregation aggregation = Aggregation.newAggregation(
				projectionOperation,
				Aggregation.sort(Sort.Direction.DESC, "date"),
				Aggregation.sort(Sort.Direction.ASC, "title")
		);
	
		AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "archives", Document.class);
	
		return results.getMappedResults();
	}	

}
