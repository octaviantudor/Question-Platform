package com.analizasoft.questionplatform.domain.mapper;

import com.analizasoft.questionplatform.domain.dto.response.AnswerDto;
import com.analizasoft.questionplatform.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "user.username", target = "answeredBy")
    @Mapping(source = "id", target = "answerId")
    AnswerDto toDto(Answer answer);
}
