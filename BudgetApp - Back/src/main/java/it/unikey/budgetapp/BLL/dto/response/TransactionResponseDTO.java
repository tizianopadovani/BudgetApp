package it.unikey.budgetapp.BLL.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TransactionResponseDTO {

    private UUID id;
    private LocalDate transactionDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Double amount;
    private Boolean isIncome;
    private String description;
    private String label;
    private WalletResponseDTO walletResponseDTO;

}
