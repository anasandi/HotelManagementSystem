package com.example.hotelmanagenetsystem.repository;

import com.example.hotelmanagenetsystem.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findByEmail(String email);
	//UserProfile findByEmail(String email);
     
}