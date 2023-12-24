package com.example.flowback.payload;

import java.util.List;

public record HomeResponse(
        List<FixedExtResponse> fixedExtResponse,
        List<CustomExtResponse> customExtResponse

) {
}
