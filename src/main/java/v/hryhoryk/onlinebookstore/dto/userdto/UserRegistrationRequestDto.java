package v.hryhoryk.onlinebookstore.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import v.hryhoryk.onlinebookstore.validation.FieldMatch;

@FieldMatch(firstField = "password", secondField = "repeatPassword",
        message = "Passwords must match, try again")
public record UserRegistrationRequestDto(
        @NotBlank(message = "Email field can't be empty!")
        @Email
        @Size(min = 5, max = 100)
        String email,
        @NotBlank(message = "We want to know how to call you by name, please enter it")
        @Size(min = 2, max = 100)
        String firstName,
        @NotBlank(message = "Your surname is also important to us, please add it")
        @Size(min = 2, max = 100)
        String lastName,
        @NotBlank(message = "Password field can't be empty!")
        @NotNull(message = "Please, enter your shipping address")
        @Size(min = 8, max = 150)
        String shippingAddress,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
                message = """
                        Password must be 8+ characters long, contain at least one special character,
                        one digit, one lowercase and uppercase letter.
                        """)
        @Size(min = 8, max = 100)
        String password,
        @NotBlank(message = "You need to repeat your password")
        @Size(min = 8, max = 100)
        String repeatPassword
) {
}
