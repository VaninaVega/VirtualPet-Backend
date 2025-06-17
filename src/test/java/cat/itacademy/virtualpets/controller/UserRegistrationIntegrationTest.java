package cat.itacademy.virtualpets.controller;

import cat.itacademy.virtualpets.services.KafkaUserEventProducer;
import cat.itacademy.virtualpets.dto.UserRegistrationDTO;
import cat.itacademy.virtualpets.model.User;
import cat.itacademy.virtualpets.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private KafkaUserEventProducer kafkaUserEventProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    void shouldRegisterUserAndPublishKafkaEvent() throws Exception {
        // Arrange
        File jsonFile = new File("src/test/resources/user-registration-request.json");
        UserRegistrationDTO registrationDTO = objectMapper.readValue(jsonFile, UserRegistrationDTO.class);
        String jsonRequest = objectMapper.writeValueAsString(registrationDTO);

        // Verifico que el usuario no existe antes de la prueba
        assertFalse(userRepository.findById(registrationDTO.getUserName()).isPresent());

        // Como no existe, se crea un nuevo usuario
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"message\":\"User registered successfully\"}"));

        // Validación 1: se guardó en la base de datos
        Optional<User> savedUser = userRepository.findById(registrationDTO.getUserName());
        assertTrue(savedUser.isPresent());
        assertEquals(registrationDTO.getEmail(), savedUser.get().getEmail());

        // Validación 2: se llamó al productor Kafka
        verify(kafkaUserEventProducer, times(1))
                .publishUserRegisteredEvent(argThat(user ->
                        user.getUserName().equals(registrationDTO.getUserName()) &&
                                user.getEmail().equals(registrationDTO.getEmail())));
    }
}
