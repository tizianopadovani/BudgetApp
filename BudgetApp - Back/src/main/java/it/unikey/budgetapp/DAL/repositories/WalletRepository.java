package it.unikey.budgetapp.DAL.repositories;

import it.unikey.budgetapp.DAL.entities.Wallet;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query
    List<Wallet> getWalletsByUserId(UUID userId);

}
