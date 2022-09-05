package it.unikey.budgetapp.BLL.mapper.implementation.request;

import it.unikey.budgetapp.BLL.dto.request.UserRequestDTO;
import it.unikey.budgetapp.BLL.mapper.abstraction.GenericRequestMapper;
import it.unikey.budgetapp.DAL.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper extends GenericRequestMapper<User, UserRequestDTO> {



}
