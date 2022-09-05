package it.unikey.budgetapp.DAL.repositories;

import it.unikey.budgetapp.DAL.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query
    List<Transaction> getTransactionsByWalletId(UUID walletId);

    @Query(value = "SELECT * FROM [transaction] INNER JOIN wallet ON [transaction].wallet_id = wallet.id WHERE wallet.user_id = :userId", nativeQuery = true)
    List<Transaction> getTransactionByUserId(@Param("userId") UUID userId);

}
