package com.example.flowback.service;

import com.example.flowback.entity.FixedExtension;
import com.example.flowback.payload.FixedExtUpdateForm;
import com.example.flowback.repository.FixedExtensionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class FixedExtServiceTest {

    @Mock
    private FixedExtensionRepository fixedExtensionRepository;

    @InjectMocks
    private FixedExtService fixedExtService;

    @DisplayName("고정 확장자 활성화/비활성화")
    @ParameterizedTest
    @MethodSource("flag")
    void updateFixedExtensionActive(boolean flag) {
        FixedExtension fixedExt = mock(FixedExtension.class);
        FixedExtUpdateForm form = mock(FixedExtUpdateForm.class);
        given(fixedExtensionRepository.findById(anyLong())).willReturn(Optional.of(fixedExt));
        given(form.flag()).willReturn(flag);

        fixedExtService.updateFixedExtensionActive(1L, form);

        then(fixedExt).should().setActive(flag);
    }
    static Stream<Boolean> flag() {
        return Stream.of(
                true,
                false
        );
    }


}