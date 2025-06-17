package cat.itacademy.virtualpets.services;

import cat.itacademy.virtualpets.dto.CreatePetDTO;
import cat.itacademy.virtualpets.dto.PetResponseDTO;
import cat.itacademy.virtualpets.exceptions.PetAlreadyExistException;
import cat.itacademy.virtualpets.mapper.PetMapper;
import cat.itacademy.virtualpets.model.Pet;
import cat.itacademy.virtualpets.model.User;
import cat.itacademy.virtualpets.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;


    private static final int DEFAULT_ENERGY = 50;
    private static final boolean DEFAULT_HUNGRY = true;
    private static final int DEFAULT_FUN = 50;

    public PetResponseDTO createPet(CreatePetDTO createPetDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean petExists = petRepository.findByNameAndOwnerUserName(createPetDTO.getName(), username).isPresent();
        if (petExists) {
            throw new PetAlreadyExistException("A pet with the same name already exists.");
        }

        Pet pet = new Pet();
        pet.setName(createPetDTO.getName());
        pet.setType(createPetDTO.getType());
        pet.setColor(createPetDTO.getColor());
        pet.setEnergy(DEFAULT_ENERGY);
        pet.setHungry(DEFAULT_HUNGRY);
        pet.setFun(DEFAULT_FUN);
        User owner = new User();
        owner.setUserName(username);
        pet.setOwner(owner);


        Pet savedPet = petRepository.save(pet);
        return petMapper.toResponseDto(savedPet);
    }
}