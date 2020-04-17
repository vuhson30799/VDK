package packages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Length(min = 8,message = "Username has at least 8 characters")
    @NotEmpty(message = "Please enter your username")
    private String username;

    @Length(min = 8, message = "Password has at least 8 characters")
    @NotEmpty(message = "Please enter your password")
    private String password;

    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String department;

    private String role;
}
