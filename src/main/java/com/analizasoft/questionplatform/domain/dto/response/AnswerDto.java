package com.analizasoft.questionplatform.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    private String answerId;

    private String answer;

    private Boolean isBestAnswer;

    private String answeredBy;
}
