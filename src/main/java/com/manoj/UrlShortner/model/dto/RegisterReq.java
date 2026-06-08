package com.manoj.UrlShortner.model.dto;

import lombok.Data;

import java.util.Set;
@Data
public class RegisterReq {
    private String name;
    private String username;
    private String email;
    private Set<String> role;
    private String password;

}
