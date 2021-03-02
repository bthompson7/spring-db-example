package com.springdatabase.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springdatabase.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	//custom CRUD operations
	//select
	@Query(value="SELECT * FROM user where name like %:name%", nativeQuery = true)
	List<User> findByName(@Param("name") String name);
	
	@Query(value="SELECT * FROM user where id = :id", nativeQuery = true)
	List<User> findById(@Param("id") int id);

	//update
	@Transactional
	@Modifying
	@Query(value="UPDATE user SET name = :name, email = :email where id = :id", nativeQuery = true)
	int updateUser(@Param("id") int id,@Param("name") String name,@Param("email") String email);
	
	//delete
	@Transactional
	@Modifying
	@Query(value="DELETE FROM user where id = :id", nativeQuery = true)
	int deleteUser(@Param("id") int id);
	
	
	
	
	
	
}
