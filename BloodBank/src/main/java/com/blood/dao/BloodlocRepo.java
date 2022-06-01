package com.blood.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blood.entities.Bloodloc;

public interface BloodlocRepo extends CrudRepository<Bloodloc,Integer>{
	
	
	@Query("From Bloodloc Where cityname= ?1")
	public List<Bloodloc> findByCitynames(String city);
	
//	@Query("FROM Author WHERE firstName = ?1 ORDER BY lastName ASC")
//    List<Author> findByFirstNameOrderByLastname(String firstName);
}
