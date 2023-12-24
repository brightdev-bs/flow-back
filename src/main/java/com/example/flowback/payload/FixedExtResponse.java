package com.example.flowback.payload;

import com.example.flowback.entity.CustomExtension;
import com.example.flowback.entity.FixedExtension;

public record FixedExtResponse(
        Long id,
        String extension,
        boolean active
) {
    public static FixedExtResponse from(FixedExtension fixedExtension) {
        return new FixedExtResponse(
                fixedExtension.getId(),
                fixedExtension.getExtension(),
                fixedExtension.isActive()
        );
    }
}
