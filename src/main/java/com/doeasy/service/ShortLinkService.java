package com.doeasy.service;


import java.util.List;

import com.doeasy.config.CacheConfig;
import com.doeasy.entity.ShortLink;
import com.doeasy.repository.ShortLinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkService {

    private ShortLinkRepository shortLinkRepository;

    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    public ShortLink save(ShortLink url) {
    	return this.shortLinkRepository.save(url);
    }

    public List<ShortLink> findAll() {
        return this.shortLinkRepository.findAll();
    }

    @Cacheable(cacheNames = CacheConfig.SHORTLINK_CACHE, unless = "#result == null")
    public ShortLink findById(long id) {
        return this.shortLinkRepository.findShortLink(id).orElse(null);
    }

    @CachePut(cacheNames = CacheConfig.SHORTLINK_CACHE, key = "#id", unless = "#result == null")
    public ShortLink updateCache(long id) {        
        return this.shortLinkRepository.findShortLink(id).orElse(null);
    }

    @CacheEvict(cacheNames = CacheConfig.SHORTLINK_CACHE, key = "#id")
    public void deleteById(long id) {
        this.shortLinkRepository.deleteById(id);
    }    
}
