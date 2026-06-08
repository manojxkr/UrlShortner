package com.manoj.UrlShortner.controller;

import com.manoj.UrlShortner.model.UserModel;
import com.manoj.UrlShortner.model.dto.LoginReq;
import com.manoj.UrlShortner.model.dto.RegisterReq;
import com.manoj.UrlShortner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginReq loginReq) {

        return ResponseEntity.ok(userService.login(loginReq));
    }

    @PostMapping("/public/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterReq registerReq) {
        try {

            UserModel userModel = new UserModel();

            userModel.setEmail(registerReq.getEmail());
            userModel.setName(registerReq.getName());
            userModel.setUsername(registerReq.getUsername());
            userModel.setPassword(registerReq.getPassword());
            userModel.setRole("ROLE_USER");
            userService.register(userModel);
            return new ResponseEntity<>("USER register success", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }
}
