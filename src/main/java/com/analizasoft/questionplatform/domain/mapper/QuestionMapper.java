package com.analizasoft.questionplatform.domain.mapper;

import com.analizasoft.questionplatform.domain.dto.response.QuestionDto;
import com.analizasoft.questionplatform.domain.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class, SuggestionMapper.class})
public interface QuestionMapper {

    @Mapping(source = "id", target = "questionId")
    @Mapping(source = "user.username", target = "questioningUser")
    QuestionDto toDto(Question question);
}
