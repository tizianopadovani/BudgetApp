package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.response.UserResponseDTO;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/email")
@CrossOrigin("http://localhost:4200")
public class UserByEmail {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {

        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);

    }

}
