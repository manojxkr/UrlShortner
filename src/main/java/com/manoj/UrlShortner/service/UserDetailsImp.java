package com.manoj.UrlShortner.service;
import com.manoj.UrlShortner.model.UserModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Data
@NoArgsConstructor
public class UserDetailsImp implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;
    private String password;
    private Collection< ? extends GrantedAuthority> authorities;

    public UserDetailsImp(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public  static UserDetailsImp build (UserModel user)
    {
       GrantedAuthority authority= new SimpleGrantedAuthority( user.getRole() );
       return new UserDetailsImp(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
               java.util.Collections.singletonList(authority)
        
       );
    }
}
