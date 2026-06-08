package com.manoj.UrlShortner.service;

import com.manoj.UrlShortner.model.UserModel;
import com.manoj.UrlShortner.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserModel user= userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found" + username));
      return  UserDetailsImp.build(user);
    }
}
