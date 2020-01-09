package com.saran.webfluxdemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.saran.webfluxdemo.model.Tweet;


@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

}
