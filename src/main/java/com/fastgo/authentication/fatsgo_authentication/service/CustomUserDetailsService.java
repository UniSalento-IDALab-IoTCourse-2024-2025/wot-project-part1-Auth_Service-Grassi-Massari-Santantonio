package com.fastgo.authentication.fatsgo_authentication.service;



import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fastgo.authentication.fatsgo_authentication.domain.User;
import com.fastgo.authentication.fatsgo_authentication.dto.RegistrationResultDTO;
import com.fastgo.authentication.fatsgo_authentication.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

   // @Autowired
    //private JwtUtilities jwtUtilities;

   // @Autowired
   // private RabbitMqProducer rabbitMqProducer;


    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final String PHONE_REGEX = "^(\\+39)?3[0-9]{9}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty() ) {
            throw new UsernameNotFoundException(username);
        }

        return org.springframework.security.core.userdetails.User.withUsername(user.get().getUsername()).password(user.get().getPassword()).roles(user.get().getRole().toString()).build();
    }


     public Boolean usernameControl(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isEmpty();
    }

        public ResponseEntity<RegistrationResultDTO> checkCredentials(String email, String phone, String username) {
        RegistrationResultDTO registrationResultDTO = new RegistrationResultDTO();

        // Controllo se l'email è già presente
        if (userRepository.findByEmail(email).isPresent()) {
            registrationResultDTO.setMessage("Email already exists");
            registrationResultDTO.setResult(RegistrationResultDTO.EMAIL_ALREADY_EXISTS);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.CONFLICT); // Risposta 409 Conflict per email già esistente
        }
        // Controllo se l'email ha una sintassi errata
        else if (!isValidEmail(email)) {
            registrationResultDTO.setMessage("Email syntax error");
            registrationResultDTO.setResult(RegistrationResultDTO.EMAIL_SYNTAX_ERROR);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.BAD_REQUEST); // Risposta 400 Bad Request per sintassi email errata
        }


        // Controllo se il numero di telefono ha una sintassi errata
        else if (!isValidPhoneNumber(phone)) {
            registrationResultDTO.setMessage("Phone syntax error");
            registrationResultDTO.setResult(RegistrationResultDTO.PHONE_SYNTAX_ERROR);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.BAD_REQUEST); // Risposta 400 Bad Request per sintassi telefono errata
        }

        if (userRepository.findByUsername(username).isPresent()) {
            registrationResultDTO.setMessage("Username already exists");
            registrationResultDTO.setResult(RegistrationResultDTO.USERNAME_ALREADY_EXISTS);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.CONFLICT); // Risposta 409 Conflict per email già esistente
        }

        return new ResponseEntity<>(registrationResultDTO, HttpStatus.OK);
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    public ResponseEntity<RegistrationResultDTO> checkCredentials(String email, String username) {
        RegistrationResultDTO registrationResultDTO = new RegistrationResultDTO();

        // Controllo se l'email è già presente
        if (userRepository.findByEmail(email).isPresent()) {
            registrationResultDTO.setMessage("Email already exists");
            registrationResultDTO.setResult(RegistrationResultDTO.EMAIL_ALREADY_EXISTS);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.CONFLICT); // Risposta 409 Conflict per email già esistente
        }
        // Controllo se l'email ha una sintassi errata
        else if (!isValidEmail(email)) {
            registrationResultDTO.setMessage("Email syntax error");
            registrationResultDTO.setResult(RegistrationResultDTO.EMAIL_SYNTAX_ERROR);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.BAD_REQUEST); // Risposta 400 Bad Request per sintassi email errata
        }


        if (userRepository.findByUsername(username).isPresent()) {
            registrationResultDTO.setMessage("Username already exists");
            registrationResultDTO.setResult(RegistrationResultDTO.USERNAME_ALREADY_EXISTS);
            return new ResponseEntity<>(registrationResultDTO, HttpStatus.CONFLICT); // Risposta 409 Conflict per username già esistente
        }

        return new ResponseEntity<>(registrationResultDTO, HttpStatus.OK);
    }


  

     public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}