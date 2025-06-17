package cat.itacademy.virtualpets.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {
    private String userName;
    private String email;
}

