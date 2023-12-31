package v.hryhoryk.onlinebookstore.service.user;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import v.hryhoryk.onlinebookstore.dto.userdto.UserRegistrationRequestDto;
import v.hryhoryk.onlinebookstore.dto.userdto.UserResponseDto;
import v.hryhoryk.onlinebookstore.exceptions.RegistrationException;
import v.hryhoryk.onlinebookstore.mapper.UserMapper;
import v.hryhoryk.onlinebookstore.model.Role;
import v.hryhoryk.onlinebookstore.model.ShoppingCart;
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
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("Registration failed. User already exists.");
        }
        User user = userMapper.toUser(requestDto);
        createShoppingCart(user);
        setUserRole(user);
        setUserPassword(user, requestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    private void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);
    }

    private void setUserRole(User user) {
        Role roleByName = roleRepository.getRoleByName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(roleByName));
    }

    private void setUserPassword(User user, UserRegistrationRequestDto requestDto) {
        user.setPassword(passwordEncoder.encode(requestDto.password()));
    }
}
