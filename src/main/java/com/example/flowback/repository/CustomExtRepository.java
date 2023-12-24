package com.example.flowback.repository;

import com.example.flowback.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomExtRepository extends JpaRepository<CustomExtension, Long> {

    Optional<CustomExtension> findByExtension(String input);
}
