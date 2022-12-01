package com.analizasoft.questionplatform.service;

import com.analizasoft.questionplatform.domain.entity.User;
import com.analizasoft.questionplatform.exception.AlreadyExistsException;
import com.analizasoft.questionplatform.repository.UserRepository;
import com.analizasoft.questionplatform.security.constants.AuthConstants;
import com.analizasoft.questionplatform.security.userdetails.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;


    public User findByUsernameOrEmail(String userNameOrEmail) {
        return userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail)
                .orElseThrow(() -> new AlreadyExistsException(AuthConstants.USERNAME_OR_EMAIL_EXIST));
    }

    public User getCurrentUser() {

        var login = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByUsername(login.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
