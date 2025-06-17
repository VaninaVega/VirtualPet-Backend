package cat.itacademy.virtualpets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "Response data transfer object for user registration")
public class UserRegistrationResponseDTO {
    private String message;
}
