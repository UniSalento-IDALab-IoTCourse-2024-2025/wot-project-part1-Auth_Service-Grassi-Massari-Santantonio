package com.fastgo.authentication.fatsgo_authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fastgo.authentication.fatsgo_authentication.domain.Role;
import com.fastgo.authentication.fatsgo_authentication.domain.User;
import com.fastgo.authentication.fatsgo_authentication.dto.ClientDto;
import com.fastgo.authentication.fatsgo_authentication.dto.RiderDto;
import com.fastgo.authentication.fatsgo_authentication.dto.ShopKeeperDto;
import com.fastgo.authentication.fatsgo_authentication.dto.UpdateStatusDto;
import com.fastgo.authentication.fatsgo_authentication.repositories.UserRepository;
import com.fastgo.authentication.fatsgo_authentication.security.JwtUtilities;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtilities jwtUtilities;

    public boolean isAdmin(String token) {
       return jwtUtilities.hasRoleAdmin(token);
    }

   
    @SuppressWarnings("null")
    public void updateUserStatus(UpdateStatusDto dto) {
        // Trova l'utente o lancia un'eccezione se non esiste
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + dto.getUserId()));
        
     
        user.setStatus(dto.getNewStatus());
        userRepository.save(user);
    }


    
    public Optional<Object> getUserByIdAsDto(String id) {
        return userRepository.findById(id).map(this::mapUserToDto);
    }

    public List<Object> getAllUsersAsDto() {
        return userRepository.findAll()
                .stream()
                .map(this::mapUserToDto)
                .collect(Collectors.toList());
    }

  
    public List<RiderDto> getUsersByRoleRider() {
        return userRepository.findByRole(Role.RIDER)
                .map(list -> list.stream()
                                .map(this::userToRiderDto)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList()); 
    }

 
    public List<ShopKeeperDto> getUsersByRoleShopkeeper() {
        return userRepository.findByRole(Role.SHOPKEEPER)
                .map(list -> list.stream()
                                .map(this::userToShopKeeperDto)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

 
    public List<ClientDto> getUsersByRoleClient() {
        return userRepository.findByRole(Role.USER)
                .map(list -> list.stream()
                                .map(this::userToClientDto)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }


    private Object mapUserToDto(User user) {
        if (user == null) return null;

        switch (user.getRole()) {
            case RIDER:
                return userToRiderDto(user);
            case SHOPKEEPER:
                return userToShopKeeperDto(user);
            case USER:
                return userToClientDto(user);
            case ADMIN:
                return userToClientDto(user); 
            default:
                return userToClientDto(user);
        }
    }

    // Ho mantenuto le tue aggiunte per setPictureUrl
    private ClientDto userToClientDto(User user) {
        ClientDto dto = new ClientDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        dto.setPictureUrl(user.getPictureUrl()); 
        return dto;
    }

    
    private RiderDto userToRiderDto(User user) {
        RiderDto dto = new RiderDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        dto.setPictureUrl(user.getPictureUrl()); 
        return dto;
    }

    
    private ShopKeeperDto userToShopKeeperDto(User user) {
        ShopKeeperDto dto = new ShopKeeperDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        dto.setPictureUrl(user.getPictureUrl());
        return dto;
    }
}