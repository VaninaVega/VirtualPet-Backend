package cat.itacademy.virtualpets.mapper;

import cat.itacademy.virtualpets.dto.CreatePetDTO;
import cat.itacademy.virtualpets.dto.PetResponseDTO;
import cat.itacademy.virtualpets.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public CreatePetDTO toDto(Pet pet) {
        if (pet == null) {
            return null;
        }
        CreatePetDTO dto = new CreatePetDTO();
        dto.setName(pet.getName());
        dto.setType(pet.getType());
        dto.setColor(pet.getColor());

        return dto;
    }

    public PetResponseDTO toResponseDto(Pet pet) {
        if (pet == null) {
            return null;
        }
        PetResponseDTO responseDto = new PetResponseDTO();
        responseDto.setId(pet.getId());
        responseDto.setName(pet.getName());
        responseDto.setType(pet.getType());
        responseDto.setColor(pet.getColor());
        responseDto.setEnergy(pet.getEnergy());
        responseDto.setHungry(pet.isHungry());
        responseDto.setFun(pet.getFun());

        return responseDto;
    }

    public Pet toEntity(CreatePetDTO dto) {
        if (dto == null) {
            return null;
        }
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setType(dto.getType());
        pet.setColor(dto.getColor());

        return pet;
    }
}

