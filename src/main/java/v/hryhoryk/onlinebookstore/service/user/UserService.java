package v.hryhoryk.onlinebookstore.service.user;

import v.hryhoryk.onlinebookstore.dto.UserRegistrationRequestDto;
import v.hryhoryk.onlinebookstore.dto.UserResponseDto;
import v.hryhoryk.onlinebookstore.exceptions.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
