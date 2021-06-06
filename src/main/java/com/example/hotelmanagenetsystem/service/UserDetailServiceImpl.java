package com.example.hotelmanagenetsystem.service;

import com.example.hotelmanagenetsystem.model.Role;
import com.example.hotelmanagenetsystem.model.UserProfile;
import com.example.hotelmanagenetsystem.repository.RoleRepository;
import com.example.hotelmanagenetsystem.repository.UserProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserProfileRepository userProfileRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailServiceImpl(UserProfileRepository userProfileRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    /*@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	//log.debug("in UserDetailService");
        UserProfile user = userProfileRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        
        return new SecurityUser(user); 
    }*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return userProfileRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email+"not found"));
    	/*UserProfile user = userProfileRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + email + " not found");
        }
        return new SecurityUser(user); 
*/
    }

    public UserProfile register(UserProfile userProfile){
        Role role=roleRepository.findByName("ROLE_USER");
        userProfile.addRole(role);
        userProfile.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
       return this.userProfileRepository.save(userProfile);
    }
    public UserProfile findyById(long id){
        return  this.userProfileRepository.getOne(id);
    }
    
    /*public Optional<UserProfile> findByEmail(String email) {
    	return this.userProfileRepository.findByEmail(email);
    }*/
}
