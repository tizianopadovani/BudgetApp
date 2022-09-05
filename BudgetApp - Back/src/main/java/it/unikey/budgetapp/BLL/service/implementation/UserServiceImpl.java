package it.unikey.budgetapp.BLL.service.implementation;

import it.unikey.budgetapp.BLL.dto.request.UserRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.UserResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidUserException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.mapper.implementation.request.UserRequestMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.response.UserResponseMapper;
import it.unikey.budgetapp.BLL.service.abstraction.UserService;
import it.unikey.budgetapp.DAL.entities.User;
import it.unikey.budgetapp.DAL.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        if (user == null) throw new UsernameNotFoundException("Email not found!");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public void saveUser(UserRequestDTO userRequestDTO) throws InvalidUserException {

        User user = userRequestMapper.asEntity(userRequestDTO);
        if(user != null) {
            if(userRequestDTO.getFirstname().length() > 40 && userRequestDTO.getFirstname().length() < 2)
                throw new InvalidUserException("Firstname length is not valid!");
            if(userRequestDTO.getLastname().length() > 40 && userRequestDTO.getLastname().length() < 2)
                throw new InvalidUserException("Lastname length is not valid!");
            if(userRequestDTO.getPassword().length() < 7)
                throw new InvalidUserException("Password is too short (at least 7 characters)");
            else
                user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setCreatedAt(LocalDate.now());
            userRepository.save(user);
        }
        else {
            throw new InvalidUserException("User is not valid!");
        }

    }

    @Override
    public List<UserResponseDTO> findAllUsers() {

        List<User> userList = userRepository.findAll();
        return userResponseMapper.asDTOList(userList);

    }

    @Override
    public UserResponseDTO findUserById(UUID id) throws UserNotFoundException {

        if(userRepository.existsById(id)) {
            User user = userRepository.getById(id);
            return userResponseMapper.asDTO(user);
        }
        else {
            throw new UserNotFoundException("User not found!");
        }

    }

    public UserResponseDTO findUserByEmail(String email) throws UserNotFoundException {

        User user = userRepository.getUserByEmail(email);
         if(user != null){
            return userResponseMapper.asDTO(user);
        }
         else {
             throw new UserNotFoundException("Email not found!");
         }

    }

    @Override
    public void deleteUserById(UUID id) throws UserNotFoundException {

        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public void patchUserById(UUID id, UserRequestDTO userRequestDTO) throws UserNotFoundException, InvalidUserException {

        if(userRepository.existsById(id)) {
            User user = userRepository.getById(id);
            if(userRequestDTO.getFirstname() != null && userRequestDTO.getFirstname().length() <= 40 && userRequestDTO.getFirstname().length() >= 2)
                user.setFirstname(userRequestDTO.getFirstname());
            else
                throw new InvalidUserException("Firstname is not valid!");
            if(userRequestDTO.getLastname() != null && userRequestDTO.getLastname().length() <= 40 && userRequestDTO.getLastname().length() >= 2)
                user.setLastname(userRequestDTO.getLastname());
            else
                throw new InvalidUserException("Lastname is not valid!");
            if(userRequestDTO.getEmail() != null) user.setEmail(userRequestDTO.getEmail());
            if(userRequestDTO.getPassword() != null && userRequestDTO.getPassword().length() >= 7)
                user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            else
                throw new InvalidUserException("Password is too short!");
            userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("User not found!");
        }

    }

}
