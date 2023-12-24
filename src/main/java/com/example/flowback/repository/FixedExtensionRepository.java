package com.example.flowback.repository;

import com.example.flowback.entity.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, Long> {

    Optional<FixedExtension> findByExtension(String input);
}
