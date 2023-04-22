package ru.sergalas.perpay.security.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.perpay.security.constants.SecurityConstants;
import ru.sergalas.perpay.security.exceptions.UserSignException;
import ru.sergalas.perpay.security.dto.request.LoginRequestDto;
import ru.sergalas.perpay.security.dto.request.SignupRequestDto;
import ru.sergalas.perpay.security.dto.response.JWTTokenSuccessResponseDto;
import ru.sergalas.perpay.security.dto.response.MessageResponseDto;
import ru.sergalas.perpay.security.providers.JWTTokenProvider;
import ru.sergalas.perpay.security.services.UserAuthService;
import ru.sergalas.perpay.security.validators.ResponseErrorValidator;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private ResponseErrorValidator responseErrorValidator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody SignupRequestDto signupRequest, BindingResult bindingResult) throws UserSignException {
        var errors = responseErrorValidator.responseMapValidator(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }

        userAuthService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponseDto("Register success"));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDto loginRequest, BindingResult bindingResult) {
        var errors = responseErrorValidator.responseMapValidator(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponseDto(true,jwt));
    }
}
