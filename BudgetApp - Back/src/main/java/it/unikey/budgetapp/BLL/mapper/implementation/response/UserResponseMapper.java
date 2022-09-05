package it.unikey.budgetapp.BLL.mapper.implementation.response;

import it.unikey.budgetapp.BLL.dto.response.UserResponseDTO;
import it.unikey.budgetapp.BLL.mapper.abstraction.GenericResponseMapper;
import it.unikey.budgetapp.DAL.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper extends GenericResponseMapper<User, UserResponseDTO> {



}
