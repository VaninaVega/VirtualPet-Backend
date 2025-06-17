package cat.itacademy.virtualpets.repository;

import cat.itacademy.virtualpets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllPetsByOwnerUserName(String username);

    Optional<Pet> findByIdAndOwnerUserName(Long id, String username);

    Optional<Pet> findByNameAndOwnerUserName(String name, String username);
}


