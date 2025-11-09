package com.fastgo.authentication.fatsgo_authentication.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fastgo.authentication.fatsgo_authentication.dto.ClientDto;
import com.fastgo.authentication.fatsgo_authentication.dto.ListClientDto;
import com.fastgo.authentication.fatsgo_authentication.dto.ListRiderDto;
import com.fastgo.authentication.fatsgo_authentication.dto.ListShopKeeperDto;
import com.fastgo.authentication.fatsgo_authentication.dto.RiderDto;
import com.fastgo.authentication.fatsgo_authentication.dto.ShopKeeperDto;
import com.fastgo.authentication.fatsgo_authentication.dto.UpdateStatusDto;
import com.fastgo.authentication.fatsgo_authentication.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    private void checkAdmin(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Header Authorization mancante o malformato");
        }
        String token = authorizationHeader.replace("Bearer ", "");
        if (!adminService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Accesso negato");
        }
    }


   
    @GetMapping("/users/rider")
    public ResponseEntity<ListRiderDto> getUsersByRoleRider(
            @RequestHeader("Authorization") String authorizationHeader) {
        
        checkAdmin(authorizationHeader);
        List<RiderDto> users = adminService.getUsersByRoleRider();
        ListRiderDto responseDto = new ListRiderDto();
        responseDto.setRiders(users);
        return ResponseEntity.ok(responseDto);
    }

   
    @GetMapping("/users/shopkeeper")
    public ResponseEntity<ListShopKeeperDto> getUsersByRoleShopKeeper(
            @RequestHeader("Authorization") String authorizationHeader) {
        
        checkAdmin(authorizationHeader);
        List<ShopKeeperDto> users = adminService.getUsersByRoleShopkeeper();
        ListShopKeeperDto responseDto = new ListShopKeeperDto();
        responseDto.setShopKeepers(users);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/users/client")
    public ResponseEntity<ListClientDto> getUsersByRoleClient(
            @RequestHeader("Authorization") String authorizationHeader) {
        
        checkAdmin(authorizationHeader);
        List<ClientDto> users = adminService.getUsersByRoleClient();
        ListClientDto responseDto = new ListClientDto();
        responseDto.setClients(users);
        return ResponseEntity.ok(responseDto);
    }

   
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String id) {
        
        checkAdmin(authorizationHeader);
        
        return adminService.getUserByIdAsDto(id)
                .map(ResponseEntity::ok) // 200 OK con DTO
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    
    @PatchMapping("/users/status")
    public ResponseEntity<Void> updateUserStatus(
            @RequestHeader("Authorization") String authorizationHeader, 
            @RequestBody UpdateStatusDto updateStatusDto) {
        
        checkAdmin(authorizationHeader);
        
        try {
            
            adminService.updateUserStatus(updateStatusDto);
            return ResponseEntity.ok().build(); 
        } catch (RuntimeException e) {
            
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}