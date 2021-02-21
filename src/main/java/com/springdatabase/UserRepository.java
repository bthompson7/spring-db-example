package com.springdatabase;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

	//select
	@Query(value="SELECT * FROM user where name = :name", nativeQuery = true)
	List<User> findByName(@Param("name") String name);
	
	@Query(value="SELECT * FROM user where id = :id", nativeQuery = true)
	List<User> findById(@Param("id") int id);

	//update
	@Transactional
	@Modifying
	@Query(value="UPDATE user SET name = :name, email = :email where id = :id", nativeQuery = true)
	int updateUser(@Param("id") int id,@Param("name") String name,@Param("email") String email);
	
	
	
	
	
	
}
