package cat.itacademy.virtualpets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Login request data transfer object")
public class UserRegistrationDTO {

    private String userName;
    private String password;
    private String email;
}
