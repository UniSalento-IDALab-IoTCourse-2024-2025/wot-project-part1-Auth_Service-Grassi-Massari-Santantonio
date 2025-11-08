package com.fastgo.authentication.fatsgo_authentication.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastgo.authentication.fatsgo_authentication.dto.ClientDto;
import com.fastgo.authentication.fatsgo_authentication.dto.RegistrationResultDTO;
import com.fastgo.authentication.fatsgo_authentication.dto.RiderDto;
import com.fastgo.authentication.fatsgo_authentication.dto.ShopKeeperDto;

import com.fastgo.authentication.fatsgo_authentication.service.RegistrationService;
import com.fastgo.authentication.fatsgo_authentication.domain.Role;
import com.fastgo.authentication.fatsgo_authentication.domain.User;

@RestController
@RequestMapping("/registration")
public class RegistrationRestControllers {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/client/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResultDTO> saveClient(@RequestBody ClientDto userDTO) {

        ResponseEntity<RegistrationResultDTO> responseEntity = registrationService
                .checkCredentials(userDTO.getEmail(), userDTO.getUsername());
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        }

        User newUser = registrationService.registerNewUser(
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getLastName(),
                Role.USER
        );

        userDTO.setPassword("");
        userDTO.setId(newUser.getId());

        RegistrationResultDTO registrationResultDTO = new RegistrationResultDTO();
        registrationResultDTO.setResult(RegistrationResultDTO.OK);
        registrationResultDTO.setMessage("Client registration successful");

        
        
        return new ResponseEntity<>(registrationResultDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/rider/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResultDTO> saveRider(@RequestBody RiderDto userDTO) {

        ResponseEntity<RegistrationResultDTO> responseEntity = registrationService
                .checkCredentials(userDTO.getEmail(), userDTO.getUsername());
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        }

        User newUser = registrationService.registerNewUser(
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getLastName(),
                Role.RIDER
        );

        userDTO.setPassword("");
        userDTO.setId(newUser.getId());

        RegistrationResultDTO registrationResultDTO = new RegistrationResultDTO();
        registrationResultDTO.setResult(RegistrationResultDTO.OK);
        registrationResultDTO.setMessage("Rider registration successful");

        registrationService.synchronizeRider(userDTO);

        return new ResponseEntity<>(registrationResultDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/shopkeeper/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResultDTO> saveShopKeeper(@RequestBody ShopKeeperDto userDTO) {

        ResponseEntity<RegistrationResultDTO> responseEntity = registrationService
                .checkCredentials(userDTO.getEmail(), userDTO.getUsername());
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        }

        User newUser = registrationService.registerNewUser(
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getLastName(),
                Role.SHOPKEEPER
        );

        userDTO.setPassword("");
        userDTO.setId(newUser.getId());

        RegistrationResultDTO registrationResultDTO = new RegistrationResultDTO();
        registrationResultDTO.setResult(RegistrationResultDTO.OK);
        registrationResultDTO.setMessage("ShopKeeper registration successful");
        return new ResponseEntity<>(registrationResultDTO, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<String> getServiceStatus() {
        return new ResponseEntity<>("Registration Service is running!", HttpStatus.OK);
    }
}