package it.unikey.budgetapp.PL;

import it.unikey.budgetapp.BLL.dto.request.WalletRequestDTO;
import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import it.unikey.budgetapp.BLL.exception.InvalidWalletException;
import it.unikey.budgetapp.BLL.exception.UserNotFoundException;
import it.unikey.budgetapp.BLL.exception.WalletNotFoundException;
import it.unikey.budgetapp.BLL.service.abstraction.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
@CrossOrigin("http://localhost:4200")
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Void> saveWallet(@RequestBody WalletRequestDTO walletRequestDTO) throws InvalidWalletException, UserNotFoundException {

        walletService.saveWallet(walletRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<WalletResponseDTO>> getAllWallets() {

       return new ResponseEntity<>(walletService.findAllWallets(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> getWalletById(@PathVariable UUID id) throws WalletNotFoundException {

        return new ResponseEntity<>(walletService.findWalletById(id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletById(@PathVariable UUID id) throws WalletNotFoundException {

        walletService.deleteWalletById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchWalletById(@PathVariable UUID id, @RequestBody WalletRequestDTO walletRequestDTO) throws WalletNotFoundException {

        walletService.patchWalletById(id, walletRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
