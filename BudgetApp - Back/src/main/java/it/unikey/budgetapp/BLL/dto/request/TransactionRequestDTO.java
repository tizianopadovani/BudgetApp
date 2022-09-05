package it.unikey.budgetapp.BLL.dto.request;

import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequestDTO {

    private LocalDate transactionDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Double amount;
    private Boolean isIncome;
    private String description;
    private String label;
    private WalletResponseDTO walletResponseDTO;

}
