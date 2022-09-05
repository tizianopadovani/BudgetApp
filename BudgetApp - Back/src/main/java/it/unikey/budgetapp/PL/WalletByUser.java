package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.service.abstraction.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet/user")
@CrossOrigin("http://localhost:4200")
public class WalletByUser {

    private final WalletService walletService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<WalletResponseDTO>> getWalletsByUserId(@PathVariable UUID userId) throws UserNotFoundException {

        return new ResponseEntity<>(walletService.getWalletsByUserID(userId), HttpStatus.OK);

    }

}
