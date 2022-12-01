package com.analizasoft.questionplatform.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BadgeDto {

    private Integer requiredNumOfPoints;
    private String badgeName;
}
