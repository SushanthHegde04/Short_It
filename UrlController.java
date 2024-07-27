package com.UrlShortner.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UrlShortner.Model.Url;
import com.UrlShortner.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class UrlController {
@Autowired
private UrlService urlService;

@PostMapping("/shorten")

public ResponseEntity<Url> shortenUrl(@RequestBody String originalUrl)
{
	Url shortenedUrl=urlService.shortenUrl(originalUrl);
	return ResponseEntity.ok(shortenedUrl);
	
}





@GetMapping("/shorturl/{shortUrl}")
public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
    String originalUrl = urlService.getOriginalUrl("http://localhost:8080/shorturl/" + shortUrl);
    if (originalUrl != null) {
        if (originalUrl.startsWith("\"") && originalUrl.endsWith("\"")) {
            originalUrl = originalUrl.substring(1, originalUrl.length() - 1);
        }
        response.sendRedirect(originalUrl);
    } else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}

@GetMapping("/redirect/{url}")
public void redirectToUrl(@PathVariable String url, HttpServletResponse response) throws IOException {
	
	  String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8);
      response.sendRedirect(decodedUrl);
}





}
