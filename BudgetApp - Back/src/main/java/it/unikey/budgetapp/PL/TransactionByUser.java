package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.response.TransactionResponseDTO;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.service.abstraction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction/user")
@CrossOrigin("http://localhost:4200")
public class TransactionByUser {

    private final TransactionService transactionService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByUserId(@PathVariable UUID userId) throws UserNotFoundException {

        return new ResponseEntity<>(transactionService.getTransactionByUserId(userId), HttpStatus.OK);

    }

}
