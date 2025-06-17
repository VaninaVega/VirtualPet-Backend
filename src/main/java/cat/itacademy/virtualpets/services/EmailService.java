package cat.itacademy.virtualpets.services;

import cat.itacademy.virtualpets.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EmailService {

    @KafkaListener(
            topics = "user-registered",
            groupId = "email-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        sendWelcomeEmail(event.getEmail(), event.getUserName());
    }

    @Async
    public void sendWelcomeEmail(String to, String username) {
        log.info("Simulating sending email to: {}", to);
        System.out.println("Welcome " + username + " (" + to + ")");
    }
}