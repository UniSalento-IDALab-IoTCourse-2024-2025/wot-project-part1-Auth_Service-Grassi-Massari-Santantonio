package com.fastgo.authentication.fatsgo_authentication.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.fastgo.authentication.fatsgo_authentication.domain.Role;
import com.fastgo.authentication.fatsgo_authentication.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @SuppressWarnings("null")
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<List<User>>  findByRole(Role role);

    void deleteUserByUsername(String username);
    
}
