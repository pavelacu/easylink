package com.doeasy.repository;

import java.util.Optional;
import javax.transaction.Transactional;

import com.doeasy.entity.ShortLink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {

	@Modifying(clearAutomatically=true, flushAutomatically = true)
	@Transactional
	/*
	@Query(value ="UPDATE user SET salary = :salary WHERE id = :id",
		   nativeQuery = true)
    public int updateSalary(@Param("key") Long id, @Param("salary") float salary);*/
	
	/* Use only as example to log when call repository from service*/
    default public Optional<ShortLink> findShortLink(Long key)  {
    	System.out.println("Call repository to get key=" + key);
    	return this.findById(key);
    }
}