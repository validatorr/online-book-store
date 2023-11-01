package v.hryhoryk.onlinebookstore.service.user;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import v.hryhoryk.onlinebookstore.dto.UserRegistrationRequestDto;
import v.hryhoryk.onlinebookstore.dto.UserResponseDto;
import v.hryhoryk.onlinebookstore.exceptions.RegistrationException;
import v.hryhoryk.onlinebookstore.mapper.UserMapper;
import v.hryhoryk.onlinebookstore.model.Role;
import v.hryhoryk.onlinebookstore.model.User;
import v.hryhoryk.onlinebookstore.repository.role.RoleRepository;
import v.hryhoryk.onlinebookstore.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("Registration failed. User already exists.");
        }
        Role roleByName = roleRepository.getRoleByName(Role.RoleName.ROLE_USER);
        User user = userMapper.toUser(requestDto);
        user.setRoles(Set.of(roleByName));
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
