package com.fastgo.authentication.fatsgo_authentication.restControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fastgo.authentication.fatsgo_authentication.domain.State;
import com.fastgo.authentication.fatsgo_authentication.domain.User;
import com.fastgo.authentication.fatsgo_authentication.dto.AuthenticationResponseDto;
import com.fastgo.authentication.fatsgo_authentication.dto.LoginDto;
import com.fastgo.authentication.fatsgo_authentication.security.JwtUtilities;
import com.fastgo.authentication.fatsgo_authentication.service.CustomUserDetailsService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class LoginRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtilities jwtUtilities;



    @PostMapping(value="/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        
        if (loginDto.getUsername() == null || loginDto.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password must not be empty"));
        }

         Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> user = customUserDetailsService.findByUsername(authentication.getName());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Invalid username"));
        }

        if(user.get().getStatus().equals(State.ACTIVE) && user.get().isSynchronized()){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.get().getId());
        claims.put("role", user.get().getRole().toString());
        final String jwt = jwtUtilities.generateToken(user.get().getUsername(), claims);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt, user.get().getRole()));}
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "User account is not active or not synchronized yet"));
        }

    }
    
    
}
