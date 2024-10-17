package com.learn.spring_security_demo.service;

import com.learn.spring_security_demo.entity.Users;
import com.learn.spring_security_demo.model.UserPrincipal;
import com.learn.spring_security_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    // as this overridden method says from where we need to load user by user name.
    // that is database.
    // We want this method to connect with user-repo
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findByUsername(username);

        if(users == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        // if we found a user, then we need to return an object of userDetail.
//        Since UserDetails is an interface we need to look for a class that implements UserDetails or
//        we need to create our own class implementing UserDetails
       // Ideal way is create a new class. 'Keep in mind this is one time setup'
//        Lets create a class in model as UserPrincipal
//        In terms of spring security, UserPrincipal refers to the current user who is trying to login
        return new UserPrincipal(users);
    }
}
