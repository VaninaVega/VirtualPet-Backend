package cat.itacademy.virtualpets.controllers;

import cat.itacademy.virtualpets.dto.CreatePetDTO;
import cat.itacademy.virtualpets.dto.PetResponseDTO;
import cat.itacademy.virtualpets.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final PetService petService;

    @GetMapping("/pets")
    @Operation(summary = "Recover all pets")
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        final List<PetResponseDTO> allPets = petService.getAllPets();
        return ResponseEntity.ok(allPets);
    }

    @PutMapping("/pets/{id}")
    @Operation(summary = "Update existing pet")
    public ResponseEntity<PetResponseDTO> updatePetById(@PathVariable Long id, @RequestBody CreatePetDTO createPetDTO) {
        return petService.updatePetById(id, createPetDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/pets/{id}")
    @Operation(summary = "Delete pet")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }
}
