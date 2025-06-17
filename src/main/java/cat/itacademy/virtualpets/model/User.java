package cat.itacademy.virtualpets.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "username")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> role;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
