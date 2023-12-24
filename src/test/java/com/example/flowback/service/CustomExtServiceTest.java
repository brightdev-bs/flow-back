package com.example.flowback.service;

import com.example.flowback.entity.CustomExtension;
import com.example.flowback.entity.FixedExtension;
import com.example.flowback.payload.CustomExtForm;
import com.example.flowback.repository.CustomExtRepository;
import com.example.flowback.repository.FixedExtensionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CustomExtServiceTest {

    @Mock
    private CustomExtRepository customExtRepository;

    @Mock
    private FixedExtensionRepository fixedExtensionRepository;

    @InjectMocks
    private CustomExtService customExtService;

    @DisplayName("커스텀 확장자 저장 테스트")
    @Test
    void saveCustomExt() {
        given(customExtRepository.findByExtension(anyString())).willReturn(Optional.empty());
        given(fixedExtensionRepository.findByExtension(anyString())).willReturn(Optional.empty());

        CustomExtForm form = new CustomExtForm("test");
        customExtService.saveCustomExt(form);

        then(customExtRepository).should().save(any(CustomExtension.class));
    }

    @DisplayName("커스텀 확장자 저장 테스트 - 커스텀 확장자와 중복")
    @Test
    void saveCustomExtDuplicatedWithCustom() {
        given(customExtRepository.findByExtension(anyString())).willReturn(Optional.of(new CustomExtension("test")));
        given(fixedExtensionRepository.findByExtension(anyString())).willReturn(Optional.empty());

        CustomExtForm form = new CustomExtForm("test");
        assertThrows(DuplicateKeyException.class, () -> customExtService.saveCustomExt(form));
    }

    @DisplayName("커스텀 확장자 저장 테스트 - 고정 확장자와 중복")
    @Test
    void saveCustomExtDuplicatedWithFixed() {
        given(customExtRepository.findByExtension(anyString())).willReturn(Optional.empty());
        given(fixedExtensionRepository.findByExtension(anyString())).willReturn(Optional.of(mock(FixedExtension.class)));

        CustomExtForm form = new CustomExtForm("test");
        assertThrows(DuplicateKeyException.class, () -> customExtService.saveCustomExt(form));
    }

}