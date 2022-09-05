package it.unikey.budgetapp.BLL.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDate createdAt;

}
