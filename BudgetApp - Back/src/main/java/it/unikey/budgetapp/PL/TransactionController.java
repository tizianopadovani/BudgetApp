package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.request.TransactionRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.TransactionResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidTransactionException;
import it.unikey.budgetapp.BLL.exception.TransactionNotFoundException;
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
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> saveTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) throws WalletNotFoundException, InvalidTransactionException {

        transactionService.saveTransaction(transactionRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {

        return new ResponseEntity<>(transactionService.findAllTransactions(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable UUID id) throws TransactionNotFoundException {

        return new ResponseEntity<>(transactionService.findTransactionById(id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable UUID id) throws WalletNotFoundException {

        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchTransactionById(@PathVariable UUID id, @RequestBody TransactionRequestDTO transactionRequestDTO) throws TransactionNotFoundException {

        transactionService.patchTransactionById(id, transactionRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
