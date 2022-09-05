package it.unikey.budgetapp.BLL.dto.request;

import it.unikey.budgetapp.BLL.dto.response.UserResponseDTO;
import lombok.Data;

@Data
public class WalletRequestDTO {

    private String name;
    private UserResponseDTO userResponseDTO;

}
