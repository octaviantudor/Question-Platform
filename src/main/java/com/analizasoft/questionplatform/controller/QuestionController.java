package com.analizasoft.questionplatform.controller;

import com.analizasoft.questionplatform.domain.dto.request.QuestionRequestDto;
import com.analizasoft.questionplatform.domain.dto.response.QuestionDto;
import com.analizasoft.questionplatform.service.QuestionService;
import com.analizasoft.questionplatform.util.HttpResponse;
import com.analizasoft.questionplatform.util.HttpResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final HttpResponseUtil httpResponseUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        log.info("Creating a question...");

        questionService.createQuestion(questionRequestDto);

        return ResponseEntity
                .ok(httpResponseUtil.createHttpResponse(HttpStatus.CREATED, "Question added successfully"));
    }
    @GetMapping
    public List<QuestionDto> getAllQuestions() {
        log.info("Getting all questions");

        return questionService.getAllQuestions();
    }
}
