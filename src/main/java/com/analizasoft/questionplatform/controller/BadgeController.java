package com.analizasoft.questionplatform.controller;


import com.analizasoft.questionplatform.domain.dto.response.BadgeDto;
import com.analizasoft.questionplatform.domain.dto.response.BadgeResponse;
import com.analizasoft.questionplatform.service.BadgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/badges")
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping
    public BadgeResponse getUserBadges(){
        log.info("Querying user badges...");
        return badgeService.getUserBadges();
    }

}
