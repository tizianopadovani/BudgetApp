package it.unikey.budgetapp.BLL.service.abstraction;

import it.unikey.budgetapp.BLL.dto.request.WalletRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidWalletException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.exception.WalletNotFoundException;

import java.util.List;
import java.util.UUID;

public interface WalletService {

    void saveWallet(WalletRequestDTO walletRequestDTO) throws InvalidWalletException, UserNotFoundException;

    List<WalletResponseDTO> findAllWallets();

    WalletResponseDTO findWalletById(UUID id) throws WalletNotFoundException;

    void deleteWalletById(UUID id) throws WalletNotFoundException;

    void patchWalletById(UUID id, WalletRequestDTO walletRequestDTO) throws WalletNotFoundException;

    List<WalletResponseDTO> getWalletsByUserID(UUID userId) throws UserNotFoundException;

}
