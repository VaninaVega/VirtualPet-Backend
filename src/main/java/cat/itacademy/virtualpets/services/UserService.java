package cat.itacademy.virtualpets.services;

import cat.itacademy.virtualpets.config.JWTAuthtenticationConfig;
import cat.itacademy.virtualpets.dto.LoginRequestDTO;
import cat.itacademy.virtualpets.dto.UserRegistrationDTO;
import cat.itacademy.virtualpets.exceptions.UserAlreadyExistsException;
import cat.itacademy.virtualpets.model.Role;
import cat.itacademy.virtualpets.model.User;
import cat.itacademy.virtualpets.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CachedUserService cachedUserService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTAuthtenticationConfig jwtAuthtenticationConfig;
    private final KafkaUserEventProducer kafkaProducer;

    public String registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsById(registrationDTO.getUserName())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setUserName(registrationDTO.getUserName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setRole(Set.of(Role.USER));
        userRepository.save(user);

        kafkaProducer.publishUserRegisteredEvent(user);
        return "User registered successfully";
    }


    public String login(LoginRequestDTO loginRequestDTO) {
        User user = cachedUserService.getUserByUsername(loginRequestDTO.getUserName());

        if (passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            return jwtAuthtenticationConfig.getJWTToken(user.getUserName(), user.getRole());
        } else {
            return null;
        }
    }
}

