package com.analizasoft.questionplatform.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long questionId;

    private String questioningUser;

    private List<AnswerDto> answers;

    private List<SuggestionDto> suggestions;

    private String questionText;

    private Boolean isAnswered;
}
