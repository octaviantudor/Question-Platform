package com.analizasoft.questionplatform.service;

import com.analizasoft.questionplatform.domain.dto.request.AnswerRequestDto;
import com.analizasoft.questionplatform.domain.dto.request.BestAnswerRequestDto;
import com.analizasoft.questionplatform.domain.entity.Answer;
import com.analizasoft.questionplatform.domain.entity.Question;
import com.analizasoft.questionplatform.domain.entity.User;
import com.analizasoft.questionplatform.domain.enums.PostType;
import com.analizasoft.questionplatform.domain.factory.PostFactory;
import com.analizasoft.questionplatform.exception.AppException;
import com.analizasoft.questionplatform.repository.AnswerRepository;
import com.analizasoft.questionplatform.repository.QuestionRepository;
import com.analizasoft.questionplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answearRepository;
    private final AuthService authService;
    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    private final PostFactory postFactory;

    public void addAnswer(AnswerRequestDto answerRequestDto) {

        var currentUser = authService.getCurrentUser();
        var question = questionRepository.findById(answerRequestDto.getQuestionId())
                .orElseThrow(() -> new AppException("Question not found! ", BAD_REQUEST.toString()));

        var answer = postFactory.createPostableEntity(question, answerRequestDto.getAnswer(), currentUser, PostType.ANSWER);

        answearRepository.save((Answer) answer);

        updateUserPoints(currentUser, 20);

    }

    private void updateUserPoints(User currentUser, Integer numOfPoints) {

        currentUser.setNumOfPoints(currentUser.getNumOfPoints() + numOfPoints);

        userRepository.save(currentUser);
    }



    public void chooseBestAnswer(BestAnswerRequestDto bestAnswerRequestDto) {

        var currentUser = authService.getCurrentUser();
        var question = questionRepository.findById(bestAnswerRequestDto.getQuestionId())
                .orElseThrow(() -> new AppException("Question not found! ", BAD_REQUEST.toString()));

        var answer = answearRepository.findById(bestAnswerRequestDto.getAnswerId())
                .orElseThrow(() -> new AppException("Answer not found! ", BAD_REQUEST.toString()));

        if (! question.getUser().getId().equals(currentUser.getId())){
            throw new AppException("You cannot choose best answer on another's person question!", BAD_REQUEST.toString());
        }
        if (answer.getUser().getId().equals(currentUser.getId())){
            throw new AppException("You cannot choose your own answer as the best one!!", BAD_REQUEST.toString());
        }

        answer.setIsBestAnswer(Boolean.TRUE);
        question.setIsAnswered(Boolean.TRUE);

        answearRepository.save(answer);
        questionRepository.save(question);

        updateUserPoints(answer.getUser(), 30);
    }
}
