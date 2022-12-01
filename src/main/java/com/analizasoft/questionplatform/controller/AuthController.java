package com.analizasoft.questionplatform.controller;

import com.analizasoft.questionplatform.domain.dto.response.JwtAuthenticationResponse;
import com.analizasoft.questionplatform.domain.dto.request.LoginRequestDto;
import com.analizasoft.questionplatform.security.jwt.JwtTokenProvider;
import com.analizasoft.questionplatform.security.userdetails.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Auth Controller, An entry class for all incoming requests
 */
@RequiredArgsConstructor
@Slf4j
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Validate the credentials and generate the jwt tokens
     *
     * @return access token and refresh token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {

        var userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        authenticate(loginRequest.getUsername(), loginRequest.getPassword(), userDetails.getAuthorities());


        var accessJwt = tokenProvider.generateAccessToken(userDetails);

        return ResponseEntity.ok(JwtAuthenticationResponse.builder().accessToken(accessJwt).build());
    }

    private void authenticate(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
    }


}
