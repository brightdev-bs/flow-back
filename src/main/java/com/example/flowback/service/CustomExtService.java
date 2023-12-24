package com.example.flowback.service;

import com.example.flowback.entity.CustomExtension;
import com.example.flowback.entity.FixedExtension;
import com.example.flowback.global.constant.ErrorCode;
import com.example.flowback.payload.CustomExtForm;
import com.example.flowback.payload.CustomExtResponse;
import com.example.flowback.repository.CustomExtRepository;
import com.example.flowback.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomExtService {
    private final FixedExtensionRepository fixedExtensionRepository;
    private final CustomExtRepository customExtRepository;

    public CustomExtResponse saveCustomExt(CustomExtForm form) {

        String input = form.input();
        Optional<CustomExtension> _extension = customExtRepository.findByExtension(input);
        Optional<FixedExtension> _extension2 = fixedExtensionRepository.findByExtension(input);
        if (_extension.isPresent() || _extension2.isPresent()) {
            throw new DuplicateKeyException(ErrorCode.EXTENSION_DUPLICATED.getMessage());
        }

        CustomExtension customExtension = new CustomExtension(input);
        customExtRepository.save(customExtension);

        return new CustomExtResponse(customExtension.getId(), customExtension.getExtension());
    }

    public List<CustomExtResponse> getAllCustomExt() {
        List<CustomExtension> customExtensions = customExtRepository.findAll();
        return customExtensions.stream()
                .map(customExtension -> new CustomExtResponse(customExtension.getId(), customExtension.getExtension()))
                .toList();
    }

    public void deleteCustomExt(Long id) {
        customExtRepository.deleteById(id);
    }
}
