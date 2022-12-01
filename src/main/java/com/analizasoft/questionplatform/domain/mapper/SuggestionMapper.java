package com.analizasoft.questionplatform.domain.mapper;

import com.analizasoft.questionplatform.domain.dto.response.SuggestionDto;
import com.analizasoft.questionplatform.domain.entity.Suggestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuggestionMapper {

    @Mapping(source = "user.username", target = "suggestedBy")
    SuggestionDto toDto(Suggestion suggestion);
}
