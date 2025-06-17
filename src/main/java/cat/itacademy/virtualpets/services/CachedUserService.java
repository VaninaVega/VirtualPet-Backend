package cat.itacademy.virtualpets.services;

import cat.itacademy.virtualpets.exceptions.UserNotFoundException;
import cat.itacademy.virtualpets.model.User;
import cat.itacademy.virtualpets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CachedUserService {

    private final UserRepository userRepository;

    @Cacheable(value = "users", key = "#username")
    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElseThrow(
                () -> new UserNotFoundException("User not found"));
    }
}
