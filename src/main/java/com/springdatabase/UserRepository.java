package com.springdatabase;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	//example of a custom query, make sure it's a LIST of users because we might
	//return more than 1 user
	@Query(value="SELECT * FROM user where name = :name", nativeQuery = true)
	List<User> findByName(
	  @Param("name") String name);
}
