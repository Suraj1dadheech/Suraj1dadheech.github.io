package com.blood.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.blood.entities.User;

public interface UserRepo extends CrudRepository<User,Integer> {
	
//	@Query(value="select * from User where email=email",nativeQuery=true)
	@Query("select u from User u where u.email = :email") 
	public User getUserByUserName(@Param("email") String email);
	
	 @Query("select u from User u where u.id = :id")
	 public User getUserByUserId(@Param("id") int id);
	 
	 @Query("From User Where city= ?1")
	 public List<User> findByCitynames(String city);
	 
	 @Query("select u from User u")
	 public Page<User> getAllData(Pageable pePage);
	 
//	 DELETE FROM table_name WHERE condition;
//	 @Query("delete from user u where u.id= :id")
//	 public void deleteByID
	 

}
