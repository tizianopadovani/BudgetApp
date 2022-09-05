package it.unikey.budgetapp.BLL.service.implementation;

import it.unikey.budgetapp.BLL.dto.request.WalletRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidWalletException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.exception.WalletNotFoundException;
import it.unikey.budgetapp.BLL.mapper.implementation.request.WalletRequestMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.response.UserResponseMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.response.WalletResponseMapper;
import it.unikey.budgetapp.BLL.service.abstraction.WalletService;
import it.unikey.budgetapp.DAL.entities.User;
import it.unikey.budgetapp.DAL.entities.Wallet;
import it.unikey.budgetapp.DAL.repositories.UserRepository;
import it.unikey.budgetapp.DAL.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;
    private final WalletRequestMapper walletRequestMapper;
    private final WalletResponseMapper walletResponseMapper;

    private final UserResponseMapper userResponseMapper;
    @Override
    public void saveWallet(WalletRequestDTO walletRequestDTO) throws InvalidWalletException, UserNotFoundException {

        Wallet wallet = walletRequestMapper.asEntity(walletRequestDTO);
        if(wallet != null) {
            if(walletRequestDTO.getUserResponseDTO() != null) {
                User user = userRepository.getById(walletRequestDTO.getUserResponseDTO().getId());
                user.getWalletList().add(wallet);
            }
            else {
                throw new UserNotFoundException("User not found!");
            }
            walletRepository.save(wallet);
        }
        else {
            throw new InvalidWalletException("Wallet is not valid!");
        }

    }

    @Override
    public List<WalletResponseDTO> findAllWallets() {

        List<Wallet> walletList = walletRepository.findAll();
        List<WalletResponseDTO> walletResponseDTOList = new ArrayList<>();
        for(Wallet wallet : walletList) {
            WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
            walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
            walletResponseDTOList.add(walletResponseDTO);
        }
        return walletResponseDTOList;

    }

    @Override
    public WalletResponseDTO findWalletById(UUID id) throws WalletNotFoundException {

        if(walletRepository.existsById(id)) {
            Wallet wallet = walletRepository.getById(id);
            WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
            walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
            return walletResponseDTO;
        }
        else {
            throw new WalletNotFoundException("Wallet not found!");
        }

    }

    @Override
    public void deleteWalletById(UUID id) throws WalletNotFoundException {

        if(walletRepository.existsById(id)) {
            walletRepository.deleteById(id);
        }
        else {
            throw new WalletNotFoundException("Wallet not found!");
        }

    }

    @Override
    public void patchWalletById(UUID id, WalletRequestDTO walletRequestDTO) throws WalletNotFoundException {

        if(walletRepository.existsById(id)) {
            Wallet wallet = walletRepository.getById(id);
            if(walletRequestDTO.getName() != null) wallet.setName(walletRequestDTO.getName());
            if(walletRequestDTO.getUserResponseDTO() != null) wallet.setUser(userRepository.getById(walletRequestDTO.getUserResponseDTO().getId()));
            walletRepository.save(wallet);
        }
        else {
            throw new WalletNotFoundException("Wallet not found!");
        }

    }

    @Override
    public List<WalletResponseDTO> getWalletsByUserID(UUID userId) throws UserNotFoundException {

        if(userRepository.existsById(userId)){
            List<Wallet> walletList = walletRepository.getWalletsByUserId(userId);
            List<WalletResponseDTO> walletResponseDTOList = new ArrayList<>();
            for(Wallet wallet : walletList) {
                WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
                walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
                walletResponseDTOList.add(walletResponseDTO);
            }
            return walletResponseDTOList;
        }
        else {
            throw new UserNotFoundException("User not found!");
        }

    }

}
