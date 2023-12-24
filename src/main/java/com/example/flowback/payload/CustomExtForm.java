package com.example.flowback.payload;

import com.example.flowback.entity.CustomExtension;
import com.example.flowback.global.constant.ErrorCode;
import org.hibernate.validator.constraints.Length;

public record CustomExtForm(
        @Length(min = 1, max = 20, message = "확장자는 1~20자 사이로 입력해주세요.")
        String input
) {
        public CustomExtension toEntity() {
                return new CustomExtension(input);
        }
}
