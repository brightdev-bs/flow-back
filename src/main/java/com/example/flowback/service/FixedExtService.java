package com.example.flowback.service;

import com.example.flowback.entity.FixedExtension;
import com.example.flowback.global.constant.ErrorCode;
import com.example.flowback.payload.FixedExtResponse;
import com.example.flowback.payload.FixedExtUpdateForm;
import com.example.flowback.repository.FixedExtensionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FixedExtService {
    private final FixedExtensionRepository fixedExtensionRepository;

    @Transactional
    public FixedExtResponse updateFixedExtensionActive(Long id, FixedExtUpdateForm form) {
        FixedExtension fixedExtension = fixedExtensionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EXTENSION_NOT_FOUND.getMessage()));

        fixedExtension.setActive(form.flag());
        fixedExtensionRepository.save(fixedExtension);

        return FixedExtResponse.from(fixedExtension);
    }

    public List<FixedExtResponse> getFixedExtension() {

        List<FixedExtension> result = fixedExtensionRepository.findAll();

        return result.stream()
                .map(FixedExtResponse::from)
                .collect(Collectors.toList());
    }
}
