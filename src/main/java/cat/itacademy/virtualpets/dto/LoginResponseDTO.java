package cat.itacademy.virtualpets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Login response data transfer object")
public class LoginResponseDTO {
    private String token;
    private String userName;
}

