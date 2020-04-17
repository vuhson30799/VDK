package packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 8,message = "Username has at least 8 characters")
    @NotEmpty(message = "Please enter your username")
    private String username;

    @Length(min = 8, message = "Password has at least 8 characters")
    @NotEmpty(message = "Please enter your password")
    private String password;

    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty
    private String role;
}
