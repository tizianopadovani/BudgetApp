package it.unikey.budgetapp.BLL.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDate createdAt;

}
