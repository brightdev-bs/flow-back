package com.example.flowback.controller;

import com.example.flowback.entity.FixedExtension;
import com.example.flowback.global.api.ApiResponse;
import com.example.flowback.payload.CustomExtResponse;
import com.example.flowback.payload.FixedExtResponse;
import com.example.flowback.payload.HomeResponse;
import com.example.flowback.service.CustomExtService;
import com.example.flowback.service.FixedExtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/home")
@RequiredArgsConstructor
@RestController
public class HomeController {

    private final CustomExtService customExtService;
    private final FixedExtService fixedExtService;

    @GetMapping
    public ApiResponse getHomeData() {
        List<FixedExtResponse> fixedExtensions = fixedExtService.getFixedExtension();
        List<CustomExtResponse> customExtensions = customExtService.getAllCustomExt();
        return ApiResponse.of("200", new HomeResponse(fixedExtensions, customExtensions));
    }

}
