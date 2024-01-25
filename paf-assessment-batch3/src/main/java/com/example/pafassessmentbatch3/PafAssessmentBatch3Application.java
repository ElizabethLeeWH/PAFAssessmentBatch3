package com.example.pafassessmentbatch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.pafassessmentbatch3.repositories.MongoRepository;

@SpringBootApplication
public class PafAssessmentBatch3Application implements CommandLineRunner{

	@Autowired
	MongoRepository mr;

	public static void main(String[] args) {
		SpringApplication.run(PafAssessmentBatch3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(mr.groupByCountry());
	}


}
