package cat.itacademy.virtualpets.controllers;

import cat.itacademy.virtualpets.dto.LoginRequestDTO;
import cat.itacademy.virtualpets.dto.LoginResponseDTO;
import cat.itacademy.virtualpets.dto.UserRegistrationDTO;
import cat.itacademy.virtualpets.dto.UserRegistrationResponseDTO;
import cat.itacademy.virtualpets.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        UserRegistrationResponseDTO response = new UserRegistrationResponseDTO(userService.registerUser(registrationDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user and return JWT token")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String jwtBearer = userService.login(loginRequestDTO);
        if (jwtBearer != null) {
            final LoginResponseDTO response = new LoginResponseDTO(
                    jwtBearer,
                    loginRequestDTO.getUserName()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}



