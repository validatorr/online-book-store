package v.hryhoryk.onlinebookstore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import v.hryhoryk.onlinebookstore.dto.UserLoginRequestDto;
import v.hryhoryk.onlinebookstore.dto.UserLoginResponseDto;
import v.hryhoryk.onlinebookstore.dto.UserRegistrationRequestDto;
import v.hryhoryk.onlinebookstore.dto.UserResponseDto;
import v.hryhoryk.onlinebookstore.security.AuthenticationService;
import v.hryhoryk.onlinebookstore.service.user.UserService;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
}
