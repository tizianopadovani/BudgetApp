package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.response.TransactionResponseDTO;
import it.unikey.budgetapp.BLL.exception.WalletNotFoundException;
import it.unikey.budgetapp.BLL.service.abstraction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction/wallet")
@CrossOrigin("http://localhost:4200")
public class TransactionByWallet {

    private final TransactionService transactionService;

    @GetMapping("/{walletId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByWalletId(@PathVariable UUID walletId) throws WalletNotFoundException {

        return new ResponseEntity<>(transactionService.getTransactionByWalletId(walletId), HttpStatus.OK);

    }

}
