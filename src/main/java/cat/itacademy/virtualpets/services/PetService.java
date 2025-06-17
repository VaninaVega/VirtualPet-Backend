package cat.itacademy.virtualpets.services;

import cat.itacademy.virtualpets.dto.CreatePetDTO;
import cat.itacademy.virtualpets.dto.PetResponseDTO;
import cat.itacademy.virtualpets.exceptions.PetAlreadyRestedException;
import cat.itacademy.virtualpets.exceptions.PetHasNotEnergyException;
import cat.itacademy.virtualpets.exceptions.PetNotFoundException;
import cat.itacademy.virtualpets.exceptions.PetNotHungryException;
import cat.itacademy.virtualpets.mapper.PetMapper;
import cat.itacademy.virtualpets.model.Pet;
import cat.itacademy.virtualpets.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public List<PetResponseDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(pet -> petMapper.toResponseDto(pet))
                .collect(Collectors.toList());
    }

    public List<PetResponseDTO> getAllPetsByOwner() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return petRepository.findAllPetsByOwnerUserName(username).stream()
                .map(pet -> petMapper.toResponseDto(pet))
                .collect(Collectors.toList());
    }

    public PetResponseDTO getPetById(Long id) {
        Pet pet = getPetByIdAndUsername(id);
        return petMapper.toResponseDto(pet);
    }

    public Optional<PetResponseDTO> updatePetById(Long id, CreatePetDTO createPetDTO) {
        return petRepository.findById(id).map(existingPet -> {
            if (createPetDTO.getName() != null) existingPet.setName(createPetDTO.getName());
            if (createPetDTO.getType() != null) existingPet.setType(createPetDTO.getType());
            if (createPetDTO.getColor() != null) existingPet.setColor(createPetDTO.getColor());
            Pet updatedPet = petRepository.save(existingPet);
            return petMapper.toResponseDto(updatedPet);
        });
    }

    public Optional<PetResponseDTO> updatePetByIdAndOwner(Long id, CreatePetDTO createPetDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return petRepository.findByIdAndOwnerUserName(id, username).map(existingPet -> {
            if (createPetDTO.getName() != null) existingPet.setName(createPetDTO.getName());
            if (createPetDTO.getType() != null) existingPet.setType(createPetDTO.getType());
            if (createPetDTO.getColor() != null) existingPet.setColor(createPetDTO.getColor());
            Pet updatedPet = petRepository.save(existingPet);
            return petMapper.toResponseDto(updatedPet);
        });
    }

    public void deletePetById(Long id) {
        petRepository.findById(id).map(pet -> {
            petRepository.delete(pet);
            return petMapper.toResponseDto(pet);
        });
    }

    public void deletePetByIdAndOwner(Long id) {
        Pet pet = getPetByIdAndUsername(id);
        petRepository.delete(pet);
    }

    public void play(Long id) {
        Pet pet = getPetByIdAndUsername(id);
        if (pet.getEnergy() <= 0) {
            throw new PetHasNotEnergyException("Pet has no energy to play");
        }
        pet.setEnergy(pet.getEnergy() - 10);
        pet.setFun(pet.getFun() + 15);
        petRepository.save(pet);
    }

    public void feed(Long id) {
        Pet pet = getPetByIdAndUsername(id);

        if (pet.isHungry()) {
            pet.setEnergy(Math.min(pet.getEnergy() + 20, 100)); // Limita la energía a un máximo de 100
            pet.setFun(pet.getFun() + 10);
            pet.setHungry(false); // La mascota ya no tiene hambre
            petRepository.save(pet);
        } else {
            throw new PetNotHungryException("Pet is not hungry.");
        }
    }

    public void sleep(Long id) {
        Pet pet = getPetByIdAndUsername(id);

        if (pet.getEnergy() < 100) {
            pet.setEnergy(Math.min(pet.getEnergy() + 20, 100));
            petRepository.save(pet);
        } else {
            throw new PetAlreadyRestedException("Pet is already fully rested.");
        }
    }

    private Pet getPetByIdAndUsername(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return petRepository.findByIdAndOwnerUserName(id, username)
                .orElseThrow(() -> new PetNotFoundException("Pet with ID " + id + " not found."));
    }
}

