package com.analizasoft.questionplatform.service;

import com.analizasoft.questionplatform.domain.dto.request.SuggestionRequestDto;
import com.analizasoft.questionplatform.domain.entity.Question;
import com.analizasoft.questionplatform.domain.entity.Suggestion;
import com.analizasoft.questionplatform.domain.entity.User;
import com.analizasoft.questionplatform.exception.AppException;
import com.analizasoft.questionplatform.repository.QuestionRepository;
import com.analizasoft.questionplatform.repository.SuggestionRepository;
import com.analizasoft.questionplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final AuthService authService;
    private final SuggestionRepository suggestionRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;


    public void addSuggestion(SuggestionRequestDto suggestionRequestDto) {

        var currentUser = authService.getCurrentUser();

        var question = questionRepository.findById(suggestionRequestDto.getQuestionId())
                .orElseThrow(() -> new AppException("Question not found! ", BAD_REQUEST.toString()));


        suggestionRepository.save(createSuggestion(question, suggestionRequestDto, currentUser));

        updateUserPoints(currentUser);
    }

    private void updateUserPoints(User currentUser) {
        currentUser.setNumOfPoints(currentUser.getNumOfPoints() + 10);
        userRepository.save(currentUser);
    }

    private Suggestion createSuggestion(Question question, SuggestionRequestDto suggestionRequestDto, User user) {
        if (question.getUser().getId().equals(user.getId()))
            throw new AppException("You cannot suggest on your own question!", BAD_REQUEST.toString());

        return Suggestion.builder()
                .suggestion(suggestionRequestDto.getSuggestionText())
                .question(question)
                .user(user)
                .build();
    }
}
