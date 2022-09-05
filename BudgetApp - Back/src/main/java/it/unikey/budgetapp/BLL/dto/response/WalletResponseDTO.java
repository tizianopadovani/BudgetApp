package it.unikey.budgetapp.BLL.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class WalletResponseDTO {

    private UUID id;
    private String name;
    private UserResponseDTO userResponseDTO;

}
