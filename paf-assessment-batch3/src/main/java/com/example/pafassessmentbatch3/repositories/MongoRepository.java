package com.example.pafassessmentbatch3.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.pafassessmentbatch3.models.Address;
import com.example.pafassessmentbatch3.models.Image;
import com.example.pafassessmentbatch3.models.Listing;
import com.example.pafassessmentbatch3.models.Location;
import com.example.pafassessmentbatch3.models.Search;

import java.util.*;

@Repository
public class MongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final String ATTR_COLLECTION_NAME = "listings";

    public List<Listing> convertDocumentsToListOfObjects(List<Document> documents) {
        // List<Document> documents = mongoTemplate.findAll(Document.class,
        // ATTR_COLLECTION_NAME);

        List<Listing> listings = new ArrayList<>();

        for (Document d : documents) {

            Image img = new Image();
            Document imgDoc = (Document) d.get("images");
            img.setThumbnail(imgDoc.getString("thumbnail_url"));
            img.setMedium(imgDoc.getString("medium_url"));
            img.setPicture(imgDoc.getString("picture_url"));
            img.setXlPicture(imgDoc.getString("xl_picture_url"));

            Address address = new Address();
            Location location = new Location();
            Document addressDoc = (Document) d.get("address");
            Document lDoc = (Document) addressDoc.get("location");

            location.setLocationExact(lDoc.getBoolean("is_location_exact", false));
            location.setCoordinates(lDoc.getList("coordinates", Double.class));
            location.setType(lDoc.getString("type"));

            address.setCountry(addressDoc.getString("country"));
            address.setCountryCode(addressDoc.getString("country_code"));
            address.setGovernmentArea(addressDoc.getString("government_area"));
            address.setLocation(location);
            address.setMarket(addressDoc.getString("market"));
            address.setStreet(addressDoc.getString("street"));
            address.setSuburb(addressDoc.getString("suburb"));

            Listing l = new Listing();
            l.setId(d.get("_id").toString());
            l.setName(d.get("name").toString());
            l.setDesc(d.get("description").toString());
            l.setAddress(address);
            l.setAccommodates(d.getInteger("accommodates", 0));
            l.setImageUrl(img);
            l.setAmenities(d.getList("amenities", String.class));
            l.setPrice(d.getDouble("price"));

            listings.add(l);
        }

        return listings;
    }

    // db.listings.distinct("address.country");
    public List<String> groupByCountry() {
        List<String> distinctCountry = mongoTemplate.getCollection(ATTR_COLLECTION_NAME)
                .distinct("address.country", String.class)
                .into(new ArrayList<>());
        return distinctCountry;
    };

    // db.listings.find({
    //     "address.country": "Australia", 
    //     accommodates: {$gte: 2},
    //     price: { $gte: 1, $lte: 10000} 
    // }).sort(
    //     {price: -1}
    // );// Replace w desired values
    public List<Document> searchByCountryAccPrice(Search s) {
        Query q = Query.query(Criteria.where("address.country").is(s.getCountry()).and("accommodates")
                .gte(s.getAccommodates()).and("price").gte(s.getMinPrice()).lte(s.getMaxPrice()))
                .with(Sort.by(Sort.Direction.DESC, "price"));
        List<Document> searchResults = mongoTemplate.find(q, Document.class, ATTR_COLLECTION_NAME);
        return searchResults;
    }

    public List<Document> searchById(String id) {
        Query q = Query.query(Criteria.where("_id").is(id));
        List<Document> searchResults = mongoTemplate.find(q, Document.class, ATTR_COLLECTION_NAME);
        return searchResults;
    }

    // public List<Document> sortByDescPrice(){
    // Query q = new Query().with(Sort.by(Sort.Direction.DESC, "price"));
    // return mongoTemplate.find(q, Document.class, "yourCollectionName");
    // }

    // public List<Document> sortSearchResultsByDescPrice(List<Document> searchResults) {
    //     searchResults.sort(Comparator.comparing(document -> document.getDouble("price")).reversed());
    //     return searchResults;
    // }

}
