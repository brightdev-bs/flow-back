package com.example.flowback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@ToString
@Getter
@Entity
public class FixedExtension {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String extension;

    private boolean active;

    public void setActive(boolean flag) {
        this.active = flag;
    }

    protected FixedExtension() {}
}
