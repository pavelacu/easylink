package com.doeasy.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.doeasy.entity.ShortLink;
import com.doeasy.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.google.common.math.LongMath;


@RestController
@RequestMapping(value = "/")

public class ShortLinkController {

    private ShortLinkService shortLinkService;

    @Autowired
    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @PostMapping
    public String save(@RequestBody ShortLink shortlink){
        shortlink.setUrl(cleanUrl(shortlink.getUrl()));    
        shortlink = this.shortLinkService.save(shortlink);        
        this.shortLinkService.updateCache(shortlink.getId());
        return idToShortURL(shortlink.getId());
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String key, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {        
        long id = shortURLtoID(key);
        ShortLink data = this.shortLinkService.findById(id);
        RedirectView redirectView = new RedirectView();        
        redirectView.setUrl("http://" + data.getUrl());
        return redirectView;
    }


    @GetMapping("/")
    public String index() {
      return "Hello World";
    }

    static String cleanUrl(String url){
        return url.replace("http://", "").replace("https://", "");
    }


    // Function get Id To ShortUrl
    static String idToShortURL(Long n)
    {
        // Map to store 62 possible characters
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();     
        StringBuffer shorturl = new StringBuffer();        
        // Convert given integer id to a base 62 number
        while (n > 0)
        {                        
            shorturl.append(map[LongMath.mod(n,62)]);            
            n = n / 62;
        }
        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString();
    }
     
    // Function to get integer ID back from a short url
    static long shortURLtoID(String shortURL)
    {
        long id = 0; // initialize result
     
        // A simple base conversion logic
        for (int i = 0; i < shortURL.length(); i++)
        {
            if ('a' <= shortURL.charAt(i) &&
                       shortURL.charAt(i) <= 'z')
            id = id * 62 + shortURL.charAt(i) - 'a';
            if ('A' <= shortURL.charAt(i) &&
                       shortURL.charAt(i) <= 'Z')
            id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            if ('0' <= shortURL.charAt(i) &&
                       shortURL.charAt(i) <= '9')
            id = id * 62 + shortURL.charAt(i) - '0' + 52;
        }
        return id;
    }
     
}
