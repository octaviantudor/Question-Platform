package com.analizasoft.questionplatform.domain.dto.request;

import lombok.Data;

@Data
public class BestAnswerRequestDto {

    private Long questionId;
    private Long answerId;
}
