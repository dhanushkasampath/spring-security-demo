package com.learn.spring_security_demo.service;

import com.learn.spring_security_demo.entity.Users;
import com.learn.spring_security_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users users){
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    /**
     * In this method we are taking the full control of login.
     * @param users
     * @return
     */
// after verifying I need the Authentication object
    public String verify(Users users) {
        // using the authenticationManager, we can authenticate. But how? and what?

        // What you are passing to 'authenticate()' method is unauthenticated. What you are getting is authenticated
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

        //now we can verify user is authenticated or not.
        if(authentication.isAuthenticated()){
            return "Success";
        }
        return "Fail";
    }

}
