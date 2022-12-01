package com.analizasoft.questionplatform.service;

import com.analizasoft.questionplatform.domain.dto.response.BadgeResponse;
import com.analizasoft.questionplatform.domain.mapper.BadgeMapper;
import com.analizasoft.questionplatform.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final BadgeMapper badgeMapper;
    private final AuthService authService;

    public BadgeResponse getUserBadges(){
        var currentUser = authService.getCurrentUser();

        var badgeList =  badgeRepository.getUserBadges(currentUser.getNumOfPoints())
                .stream().map(badgeMapper::toDto)
                .collect(Collectors.toList());

        return BadgeResponse.builder()
                .userPoints(currentUser.getNumOfPoints())
                .userBadges(badgeList)
                .build();

    }
}
