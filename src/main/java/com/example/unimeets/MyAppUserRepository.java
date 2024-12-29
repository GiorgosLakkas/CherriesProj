package com.example.unimeets;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long> {

    Optional<MyAppUser> findByUsername(String username);

    Optional<MyAppUser> findByName(String name);
}