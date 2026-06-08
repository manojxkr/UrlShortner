package com.manoj.UrlShortner.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manoj.UrlShortner.model.UrlMapping;
import com.manoj.UrlShortner.model.UserModel;
@Repository
public interface UrlMappingRepo extends JpaRepository<UrlMapping,Integer> {

    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(UserModel userModel);

    
} 