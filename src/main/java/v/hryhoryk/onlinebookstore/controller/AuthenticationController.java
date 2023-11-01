package v.hryhoryk.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Authentication managing",
        description = "Endpoints for managing authentication")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Log into system",
            description = "Allows registered user log into system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Logged in successfully"),
            @ApiResponse(responseCode = "403",
                    description = "Authentication failed, wrong credentials")
    })
    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @Operation(summary = "Register in the system",
            description = "Allows to register new user into the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User created - successfull registration"),
            @ApiResponse(responseCode = "400",
                    description = "Registration failed, wrong credentials")
    })
    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
}
