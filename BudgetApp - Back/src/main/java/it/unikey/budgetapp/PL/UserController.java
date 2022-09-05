package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.request.UserRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.UserResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidUserException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDTO userRequestDTO) throws InvalidUserException {

        userService.saveUser(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) throws UserNotFoundException {

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) throws UserNotFoundException {

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchUserById(@PathVariable UUID id, @RequestBody UserRequestDTO userRequestDTO) throws UserNotFoundException, InvalidUserException {

       userService.patchUserById(id, userRequestDTO);
       return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
