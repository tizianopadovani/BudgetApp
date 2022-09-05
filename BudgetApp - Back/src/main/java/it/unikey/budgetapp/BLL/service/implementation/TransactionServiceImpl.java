package it.unikey.budgetapp.BLL.service.implementation;

import it.unikey.budgetapp.BLL.dto.request.TransactionRequestDTO;
import it.unikey.budgetapp.BLL.dto.request.WalletRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.TransactionResponseDTO;
import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidTransactionException;
import it.unikey.budgetapp.BLL.exception.TransactionNotFoundException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.exception.WalletNotFoundException;
import it.unikey.budgetapp.BLL.mapper.implementation.request.TransactionRequestMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.request.UserRequestMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.request.WalletRequestMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.response.TransactionResponseMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.response.UserResponseMapper;
import it.unikey.budgetapp.BLL.mapper.implementation.response.WalletResponseMapper;
import it.unikey.budgetapp.BLL.service.abstraction.TransactionService;
import it.unikey.budgetapp.DAL.entities.Transaction;
import it.unikey.budgetapp.DAL.entities.User;
import it.unikey.budgetapp.DAL.entities.Wallet;
import it.unikey.budgetapp.DAL.repositories.TransactionRepository;
import it.unikey.budgetapp.DAL.repositories.UserRepository;
import it.unikey.budgetapp.DAL.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRequestMapper transactionRequestMapper;
    private final TransactionResponseMapper transactionResponseMapper;
    private final WalletResponseMapper walletResponseMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public void saveTransaction(TransactionRequestDTO transactionRequestDTO) throws InvalidTransactionException, WalletNotFoundException {

        Transaction transaction = transactionRequestMapper.asEntity(transactionRequestDTO);
        if(transaction != null) {
            if(transactionRequestDTO.getWalletResponseDTO() != null) {
                Wallet wallet = walletRepository.getById(transactionRequestDTO.getWalletResponseDTO().getId());
                User user = userRepository.getById(walletRepository.getById(transactionRequestDTO.getWalletResponseDTO().getId()).getUser().getId());
                user.getWalletList().add(wallet);
                wallet.getTransactionList().add(transaction);
            }
            else {
                throw new WalletNotFoundException("Wallet not found!");
            }
            if(transactionRequestDTO.getTransactionDate() == null || !transactionRequestDTO.getTransactionDate().isBefore(LocalDate.now())
                || (transactionRequestDTO.getTransactionDate().getYear() + 1 <= LocalDate.now().getYear() && transactionRequestDTO.getTransactionDate().getDayOfYear() <= LocalDate.now().getDayOfYear())
            )
                throw new DateTimeException("Date is not valid!");
            if(transactionRequestDTO.getDescription().trim().equals(""))
                throw new NullPointerException("Description is empty!");
            if(transactionRequestDTO.getAmount() > 0) {
                double roundOff = Math.round(transactionRequestDTO.getAmount() * 100.0) / 100.0;
                transaction.setAmount(roundOff);
            }
            else {
                throw new NumberFormatException("Amount is not valid!");
            }
            transaction.setCreatedAt(LocalDate.now());
            transactionRepository.save(transaction);
        }
        else {
            throw new InvalidTransactionException("Transaction is not valid!");
        }

    }

    @Override
    public List<TransactionResponseDTO> findAllTransactions() {

        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
        for(Transaction transaction : transactionList) {
            TransactionResponseDTO transactionResponseDTO = transactionResponseMapper.asDTO(transaction);
            List<Wallet> walletList = walletRepository.findAll();
            for(Wallet wallet : walletList) {
                WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
                walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
                transactionResponseDTO.setWalletResponseDTO(walletResponseDTO);
            }
            transactionResponseDTOList.add(transactionResponseDTO);
        }
        return transactionResponseDTOList;

    }

    @Override
    public TransactionResponseDTO findTransactionById(UUID id) throws TransactionNotFoundException {

        if(transactionRepository.existsById(id)) {
            Transaction transaction = transactionRepository.getById(id);
            TransactionResponseDTO transactionResponseDTO = transactionResponseMapper.asDTO(transaction);
            Wallet wallet = walletRepository.getById(transaction.getWallet().getId());
            WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
            walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
            transactionResponseDTO.setWalletResponseDTO(walletResponseDTO);
            return transactionResponseDTO;
        }
        else {
            throw new TransactionNotFoundException("Transaction not found!");
        }

    }

    @Override
    public void deleteTransactionById(UUID id) throws WalletNotFoundException {

        if(transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        }
        else {
            throw new WalletNotFoundException("Wallet not found!");
        }

    }

    @Override
    public void patchTransactionById(UUID id, TransactionRequestDTO transactionRequestDTO) throws TransactionNotFoundException {

        if(transactionRepository.existsById(id)) {
            Transaction transaction = transactionRepository.getById(id);
            if(transactionRequestDTO.getTransactionDate() != null && !transactionRequestDTO.getTransactionDate().isAfter(LocalDate.now())
                    && (transactionRequestDTO.getTransactionDate().getYear() + 1 >= LocalDate.now().getYear() && transactionRequestDTO.getTransactionDate().getDayOfYear() >= LocalDate.now().getDayOfYear())
            )
            transaction.setTransactionDate(transactionRequestDTO.getTransactionDate());
            else
                throw new DateTimeException("Date is not valid!");
            if(transactionRequestDTO.getAmount() > 0) {
                double roundOff = Math.round(transactionRequestDTO.getAmount() * 100.0) / 100.0;
                transaction.setAmount(roundOff);
            }
            else
                throw new NumberFormatException("Amount is not valid!");
            if(transactionRequestDTO.getIsIncome() != null) transaction.setIsIncome(transactionRequestDTO.getIsIncome());
            if(transactionRequestDTO.getDescription() != null && !transactionRequestDTO.getDescription().trim().equals(""))
                transaction.setDescription(transactionRequestDTO.getDescription());
            else
                throw new NullPointerException("Description is empty!");
            transaction.setLabel(transactionRequestDTO.getLabel());
            if(transactionRequestDTO.getWalletResponseDTO() != null) transaction.setWallet(walletRepository.getById(transactionRequestDTO.getWalletResponseDTO().getId()));
            transaction.setUpdatedAt(LocalDate.now());
            transactionRepository.save(transaction);
        }
        else {
            throw new TransactionNotFoundException("Transaction not found!");
        }

    }

    @Override
    public List<TransactionResponseDTO> getTransactionByWalletId(UUID walletId) throws WalletNotFoundException {

        if(walletRepository.existsById(walletId)) {
            List<Transaction> transactionList = transactionRepository.getTransactionsByWalletId(walletId);
            List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
            for (Transaction transaction : transactionList) {
                TransactionResponseDTO transactionResponseDTO = transactionResponseMapper.asDTO(transaction);
                Wallet wallet = walletRepository.getById(walletId);
                    WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
                    walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
                    transactionResponseDTO.setWalletResponseDTO(walletResponseDTO);
                    transactionResponseDTOList.add(transactionResponseDTO);
            }
            return transactionResponseDTOList;
        }
        else {
            throw new WalletNotFoundException("Wallet not found!");
        }

    }

    @Override
    public List<TransactionResponseDTO> getTransactionByUserId(UUID userId) throws UserNotFoundException {

        if(userRepository.existsById(userId)) {
            List<Transaction> transactionList = transactionRepository.getTransactionByUserId(userId);
            List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
            for (Transaction transaction : transactionList) {
                TransactionResponseDTO transactionResponseDTO = transactionResponseMapper.asDTO(transaction);
                List<Wallet> walletList = walletRepository.getWalletsByUserId(userId);
                for (Wallet wallet : walletList) {
                    WalletResponseDTO walletResponseDTO = walletResponseMapper.asDTO(wallet);
                    walletResponseDTO.setUserResponseDTO(userResponseMapper.asDTO(wallet.getUser()));
                    transactionResponseDTO.setWalletResponseDTO(walletResponseDTO);
                }
                transactionResponseDTOList.add(transactionResponseDTO);
            }
            return transactionResponseDTOList;
        }
        else {
            throw new UserNotFoundException("User not found!");
        }

    }

}
