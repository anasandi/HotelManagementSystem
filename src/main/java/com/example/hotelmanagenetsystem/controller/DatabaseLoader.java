package com.example.hotelmanagenetsystem.controller;

import com.example.hotelmanagenetsystem.model.Gender;
import com.example.hotelmanagenetsystem.model.Role;
import com.example.hotelmanagenetsystem.model.UserProfile;
import com.example.hotelmanagenetsystem.repository.RoleRepository;
import com.example.hotelmanagenetsystem.repository.UserProfileRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final UserProfileRepository userProfileRepository;


    public DatabaseLoader(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserProfileRepository userProfileRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole=new Role();
        userRole.setName("ROLE_USER");
        Role adminRole=new Role();
        adminRole.setName("ROLE_ADMIN");

        UserProfile userProfile=new UserProfile();
        userProfile.setEmail("ana@gmail.com");
        userProfile.setFirstName("Ana");
        userProfile.setLastName("Sandi");
        userProfile.setGender(Gender.FEMALE);
        userProfile.setPhoneNumber("97125279");
        userProfile.addRole(adminRole);
        userProfile.setPassword(bCryptPasswordEncoder.encode("ana1234"));

        //this.roleRepository.save(adminRole);
       //this.roleRepository.save(userRole);
       //this.userProfileRepository.save(userProfile);

    }
}
