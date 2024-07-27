package com.UrlShortner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UrlShortner.Model.Url;

public interface UrlRepository extends JpaRepository<Url,Long>{
  Optional<Url> findByshortUrl(String shortUrl);
}
