package com.analizasoft.questionplatform.controller;

import com.analizasoft.questionplatform.domain.dto.request.AnswerRequestDto;
import com.analizasoft.questionplatform.domain.dto.request.BestAnswerRequestDto;
import com.analizasoft.questionplatform.service.AnswerService;
import com.analizasoft.questionplatform.util.HttpResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final HttpResponseUtil httpResponseUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerRequestDto answerRequestDto) {
        log.info("Adding answer for question with ID: {}", answerRequestDto.getQuestionId());
        answerService.addAnswer(answerRequestDto);
        return ResponseEntity
                .ok(httpResponseUtil.createHttpResponse(HttpStatus.CREATED, "Answer added successfully"));
    }

    @PostMapping("/best")
    public ResponseEntity<?> chooseBestAnswer(@RequestBody BestAnswerRequestDto bestAnswerRequestDto){
        log.info("Choosing best answer with ID: {}, on question with ID: {}", bestAnswerRequestDto.getAnswerId(), bestAnswerRequestDto.getQuestionId());
        answerService.chooseBestAnswer(bestAnswerRequestDto);
        return ResponseEntity
                .ok(httpResponseUtil.createHttpResponse(HttpStatus.CREATED, "Best answer added successfully"));
    }
}
