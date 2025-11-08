package com.fastgo.authentication.fatsgo_authentication.dto;

import com.fastgo.authentication.fatsgo_authentication.domain.Role;

public class AuthenticationResponseDto {
    private String jwt;
    private String role;
    
    public AuthenticationResponseDto(String jwt, Role role) {this.jwt = jwt;
    this.role = role.toString();}
    public String getJwt() {return jwt;}
    public void setJwt(String jwt) {this.jwt = jwt;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
    public void setRole(Role role) {this.role = role.toString();}
}
