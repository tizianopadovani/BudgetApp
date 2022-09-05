package it.unikey.budgetapp.BLL.service.abstraction;

import it.unikey.budgetapp.BLL.dto.request.UserRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.UserResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidUserException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void saveUser(UserRequestDTO userRequestDTO) throws InvalidUserException;

    List<UserResponseDTO> findAllUsers();

    UserResponseDTO findUserById(UUID id) throws UserNotFoundException;

    UserResponseDTO findUserByEmail(String email) throws UserNotFoundException;

    void deleteUserById(UUID id) throws UserNotFoundException;

    void patchUserById(UUID id, UserRequestDTO userRequestDTO) throws UserNotFoundException, InvalidUserException;

}
