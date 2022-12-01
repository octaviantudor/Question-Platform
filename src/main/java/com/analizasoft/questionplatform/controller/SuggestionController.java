package com.analizasoft.questionplatform.controller;

import com.analizasoft.questionplatform.domain.dto.request.SuggestionRequestDto;
import com.analizasoft.questionplatform.service.SuggestionService;
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
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;
    private final HttpResponseUtil httpResponseUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addSugestion(@RequestBody SuggestionRequestDto suggestionRequestDto){
        log.info("Creating suggestion for question with ID: {}", suggestionRequestDto.getQuestionId());
        suggestionService.addSuggestion(suggestionRequestDto);
        return ResponseEntity
                .ok(httpResponseUtil.createHttpResponse(HttpStatus.CREATED, "Suggestion added successfully"));
    }
}
