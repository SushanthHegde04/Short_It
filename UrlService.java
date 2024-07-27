package com.UrlShortner.service;

import com.UrlShortner.Model.Url;

public interface UrlService {
public	Url shortenUrl(String originalUrl);
public String getOriginalUrl(String shortUrl);

}
