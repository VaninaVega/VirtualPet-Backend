package cat.itacademy.virtualpets.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@NoArgsConstructor
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PetTypeEnum type;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private PetColorEnum color;

    @Column(name = "energy")
    private int energy;
    @Column(name = "hungry")
    private boolean hungry;
    @Column(name = "fun")
    private int fun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_username",
            referencedColumnName = "username",
            nullable = false)
    private User owner;
}

