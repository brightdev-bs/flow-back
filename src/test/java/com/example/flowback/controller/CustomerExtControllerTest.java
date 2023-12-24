package com.example.flowback.controller;

import com.example.flowback.global.constant.ErrorCode;
import com.example.flowback.payload.CustomExtForm;
import com.example.flowback.repository.CustomExtRepository;
import com.example.flowback.repository.FixedExtensionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class CustomerExtControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CustomExtRepository customExtRepository;
    @Autowired
    private FixedExtensionRepository fixedExtensionRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @DisplayName("커스텀 확장자 생성")
    @Test
    void createCustomExtension() throws Exception {
        CustomExtForm form = new CustomExtForm("test");

        mockMvc.perform(post("/extensions/custom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form))
                )
                .andDo(print())
                .andExpect(jsonPath("statusCode").value(HttpStatus.CREATED.toString()))
                .andExpect(jsonPath("$.data.extension").value("test"));
    }

    @DisplayName("커스텀 확장자 생성 실패 - 20글자 이상")
    @Test
    void createCustomExtensionFailWithLength() throws Exception {
        CustomExtForm form = new CustomExtForm("0123456789012345678901234");

        mockMvc.perform(post("/extensions/custom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form))
                )
                .andDo(print())
                .andExpect(jsonPath("statusCode").value(HttpStatus.BAD_REQUEST.toString()))
                .andExpect(jsonPath("$.data").value("[input] " + ErrorCode.EXTENSION_INVALID_EXCEPTION.getMessage()));
    }

    @DisplayName("커스텀 확장자 생성 실패 - 커스텀 확장자 중복")
    @Test
    void createCustomExtensionFailWithDuplicated() throws Exception {
        CustomExtForm form = new CustomExtForm("test");
        customExtRepository.save(form.toEntity());

        mockMvc.perform(post("/extensions/custom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form))
                )
                .andDo(print())
                .andExpect(jsonPath("statusCode").value(HttpStatus.BAD_REQUEST.toString()))
                .andExpect(jsonPath("$.data").value(ErrorCode.EXTENSION_DUPLICATED.getMessage()));
    }

}