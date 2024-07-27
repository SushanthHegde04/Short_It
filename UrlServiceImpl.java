package com.UrlShortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UrlShortner.Model.Url;
import com.UrlShortner.repository.UrlRepository;
@Service
public class UrlServiceImpl implements UrlService {
	@Autowired
	private UrlRepository urlRepository;

	@Override
	public Url shortenUrl(String originalUrl) {
		// TODO Auto-generated method stub
		Url url=new Url();
		url.setOriginalUrl(originalUrl);
	    Url savedUrl = urlRepository.save(url); String shortUrl = generateShortUrl(savedUrl.getId());
        savedUrl.setShortUrl("http://localhost:8080/shorturl/"+shortUrl);
        // Save again with the short URL
        return urlRepository.save(savedUrl);
	}

	@Override
	public String getOriginalUrl(String shortUrl) {
		// TODO Auto-generated method stub
		return urlRepository.findByshortUrl(shortUrl)
				.map(Url::getOriginalUrl)
				.orElse(null);
		
	}
private String generateShortUrl(Long id)
{
	return "shorturl"+id;
}
}
