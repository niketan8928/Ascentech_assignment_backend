package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserRepository extends JpaRepository<User,Long> {
	
    @Query(value = "SELECT * FROM get_all_users()", nativeQuery = true)
    List<User> get_All_Users();
    
    @Procedure(procedureName = "delete_single_user")
    void delete_single_user(Long userId);
    
}
