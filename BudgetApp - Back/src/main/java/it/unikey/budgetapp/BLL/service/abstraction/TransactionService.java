package it.unikey.budgetapp.BLL.service.abstraction;

import it.unikey.budgetapp.BLL.dto.request.TransactionRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.TransactionResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidTransactionException;
import it.unikey.budgetapp.BLL.exception.TransactionNotFoundException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.exception.WalletNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    void saveTransaction(TransactionRequestDTO transactionRequestDTO) throws InvalidTransactionException, WalletNotFoundException;

    List<TransactionResponseDTO> findAllTransactions();

    TransactionResponseDTO findTransactionById(UUID id) throws TransactionNotFoundException;

    void deleteTransactionById(UUID id) throws WalletNotFoundException;

    void patchTransactionById(UUID id, TransactionRequestDTO transactionRequestDTO) throws TransactionNotFoundException;

    List<TransactionResponseDTO> getTransactionByWalletId(UUID walletId) throws WalletNotFoundException;

    List<TransactionResponseDTO> getTransactionByUserId(UUID userId) throws UserNotFoundException;

}
