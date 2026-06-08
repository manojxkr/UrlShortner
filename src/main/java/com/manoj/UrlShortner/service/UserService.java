package com.manoj.UrlShortner.service;

import com.manoj.UrlShortner.model.UserModel;
import com.manoj.UrlShortner.model.dto.LoginReq;
import com.manoj.UrlShortner.repo.UserRepo;
import com.manoj.UrlShortner.security.JwtAuthenticaionResponse;
import com.manoj.UrlShortner.security.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    public UserModel register(UserModel userModel) {
        if (userRepo.existsByEmail(userModel.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepo.save(userModel);
    }
    public JwtAuthenticaionResponse login(LoginReq loginReq) {
      Authentication authentication=  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(),loginReq.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        String jwt= jwtUtils.generateToken(userDetailsImp);


   return new JwtAuthenticaionResponse(jwt);

    } 
        public UserModel findByUsername(String name) {
        return userRepo.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + name)
        );
    }

}
