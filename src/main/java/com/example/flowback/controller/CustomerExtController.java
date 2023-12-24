package com.example.flowback.controller;

import com.example.flowback.global.api.ApiResponse;
import com.example.flowback.payload.CustomExtForm;
import com.example.flowback.payload.CustomExtResponse;
import com.example.flowback.service.CustomExtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/extensions/custom")
@RequiredArgsConstructor
@RestController
public class CustomerExtController {

    private final CustomExtService customExtService;

    @PostMapping
    public ApiResponse createCustomExtension(@RequestBody @Valid CustomExtForm form) {
        CustomExtResponse customExtResponse = customExtService.saveCustomExt(form);
        return ApiResponse.of(HttpStatus.CREATED.toString(), customExtResponse);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomExtension(@PathVariable Long id) {
        customExtService.deleteCustomExt(id);
        return ApiResponse.of(HttpStatus.OK.toString(), "success");
    }
}
