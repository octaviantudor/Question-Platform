package com.analizasoft.questionplatform.domain.dto.request;

import lombok.Data;

@Data
public class AnswerRequestDto {
    private Long questionId;
    private String answer;
}
