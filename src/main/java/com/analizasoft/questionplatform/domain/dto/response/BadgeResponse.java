package com.analizasoft.questionplatform.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeResponse {
    private Integer userPoints;
    private List<BadgeDto> userBadges;

}
