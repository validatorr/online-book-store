package v.hryhoryk.onlinebookstore.service.user;

import v.hryhoryk.onlinebookstore.dto.userdto.UserRegistrationRequestDto;
import v.hryhoryk.onlinebookstore.dto.userdto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
