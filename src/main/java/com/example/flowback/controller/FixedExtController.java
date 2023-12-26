package com.example.flowback.controller;

import com.example.flowback.entity.FixedExtension;
import com.example.flowback.global.api.ApiResponse;
import com.example.flowback.payload.FixedExtResponse;
import com.example.flowback.payload.FixedExtUpdateForm;
import com.example.flowback.service.FixedExtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/extensions/fixed")
@RequiredArgsConstructor
@RestController
public class FixedExtController {

    private final FixedExtService fixedExtService;

    @PatchMapping("/{id}")
    public ApiResponse updateFixedExtensionActive(@PathVariable Long id, @RequestBody FixedExtUpdateForm form) {
        FixedExtResponse response = fixedExtService.updateFixedExtensionActive(id, form);
        return ApiResponse.of(HttpStatus.OK.toString(), response);
    }
}
