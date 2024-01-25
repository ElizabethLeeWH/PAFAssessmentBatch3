package com.example.pafassessmentbatch3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pafassessmentbatch3.models.Listing;
import com.example.pafassessmentbatch3.models.Search;
import com.example.pafassessmentbatch3.repositories.MongoRepository;

import java.util.*;

@Service
public class ListingService {

    @Autowired
    private MongoRepository mongoRepo;

    public List<Listing> listofSearchResult(Search s){
        return mongoRepo.convertDocumentsToListOfObjects(mongoRepo.searchByCountryAccPrice(s));
    }

    public List<String> stringOfDistinctCountry(){
        return mongoRepo.groupByCountry();
    }

    public List<Listing> searchListingById(String id){
        return mongoRepo.convertDocumentsToListOfObjects(mongoRepo.searchById(id));
    }
}
