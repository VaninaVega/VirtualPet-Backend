package cat.itacademy.virtualpets.controllers;

import cat.itacademy.virtualpets.dto.CreatePetDTO;
import cat.itacademy.virtualpets.dto.PetResponseDTO;
import cat.itacademy.virtualpets.services.CreatePetService;
import cat.itacademy.virtualpets.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;
    private final CreatePetService createPetService;

    @GetMapping
    @Operation(summary = "Recover all pets")
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        final List<PetResponseDTO> allPets = petService.getAllPetsByOwner();
        return ResponseEntity.ok(allPets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recover pet by id")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Long id) {
        PetResponseDTO pet = petService.getPetById(id);
        if (pet != null) {
            log.debug("Pet with id {} found: {}", id, pet);
            return ResponseEntity.ok(pet);
        } else {
            log.error("Pet with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create pet")
    public ResponseEntity<PetResponseDTO> createPet(@RequestBody CreatePetDTO createPetDTO) {
        log.info("Creating pet with details: {}", createPetDTO);
        PetResponseDTO createdPet = createPetService.createPet(createPetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing pet")
    public ResponseEntity<PetResponseDTO> updatePet(@PathVariable Long id, @RequestBody CreatePetDTO createPetDTO) {
        return petService.updatePetByIdAndOwner(id, createPetDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete pet")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetByIdAndOwner(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/play")
    @Operation(summary = "Play with pet")
    public ResponseEntity<Void> play(@PathVariable Long id) {
        petService.play(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/feed")
    @Operation(summary = "Feed pet")
    public ResponseEntity<Void> feed(@PathVariable Long id) {
        petService.feed(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/sleep")
    @Operation(summary = "Sleep pet")
    public ResponseEntity<Void> sleep(@PathVariable Long id) {
        petService.sleep(id);
        return ResponseEntity.ok().build();
    }
}





