package com.analizasoft.questionplatform.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionRequestDto {
    private Long questionId;
    private String suggestionText;
}
