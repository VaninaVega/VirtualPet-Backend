package cat.itacademy.virtualpets.services;

import cat.itacademy.virtualpets.event.UserRegisteredEvent;
import cat.itacademy.virtualpets.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaUserEventProducer {

    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    @Async
    public void publishUserRegisteredEvent(User user) {
        UserRegisteredEvent event = new UserRegisteredEvent(user.getUserName(), user.getEmail());
        kafkaTemplate.send("user-registered", event);
    }
}
