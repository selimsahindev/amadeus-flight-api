package com.selimsahin.amadeus.repositories;

import com.selimsahin.amadeus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);

    Optional<User> findByUsername(String username);
}