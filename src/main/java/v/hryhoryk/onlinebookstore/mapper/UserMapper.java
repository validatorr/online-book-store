package v.hryhoryk.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import v.hryhoryk.onlinebookstore.dto.userdto.UserRegistrationRequestDto;
import v.hryhoryk.onlinebookstore.dto.userdto.UserResponseDto;
import v.hryhoryk.onlinebookstore.model.User;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface UserMapper {
    User toUser(UserRegistrationRequestDto user);

    UserResponseDto toDto(User user);
}
