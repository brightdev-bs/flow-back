package com.example.flowback.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
public class CustomExtension {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String extension;

    protected CustomExtension() {}

    public CustomExtension(String extension) {
        this.extension = extension;
    }
}
