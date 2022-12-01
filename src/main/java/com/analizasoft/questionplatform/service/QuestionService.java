package com.analizasoft.questionplatform.service;

import com.analizasoft.questionplatform.domain.dto.request.QuestionRequestDto;
import com.analizasoft.questionplatform.domain.dto.response.QuestionDto;
import com.analizasoft.questionplatform.domain.entity.Question;
import com.analizasoft.questionplatform.domain.entity.User;
import com.analizasoft.questionplatform.domain.mapper.QuestionMapper;
import com.analizasoft.questionplatform.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    private final AuthService authService;

    public void createQuestion(QuestionRequestDto questionRequestDto) {
        Question question = Question.builder()
                .questionText(questionRequestDto.getQuestionText())
                .user(authService.getCurrentUser())
                .isAnswered(Boolean.FALSE)
                .build();

        questionRepository.save(question);
    }

    public List<QuestionDto> getAllQuestions(){
        return questionRepository.findAll()
                .stream().map(questionMapper::toDto)
                .collect(Collectors.toList());
    }
}
