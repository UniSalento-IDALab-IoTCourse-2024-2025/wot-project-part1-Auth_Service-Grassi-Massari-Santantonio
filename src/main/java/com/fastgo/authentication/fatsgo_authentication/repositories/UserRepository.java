package com.fastgo.authentication.fatsgo_authentication.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import com.fastgo.authentication.fatsgo_authentication.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    void deleteUserByUsername(String username);
    
}
