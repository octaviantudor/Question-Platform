package com.analizasoft.questionplatform.domain.mapper;

import com.analizasoft.questionplatform.domain.dto.response.BadgeDto;
import com.analizasoft.questionplatform.domain.entity.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {

    BadgeDto toDto(Badge badge);
}
