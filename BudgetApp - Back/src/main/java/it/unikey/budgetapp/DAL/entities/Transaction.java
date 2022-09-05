package it.unikey.budgetapp.DAL.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "[transaction]")
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column(nullable = false)
    private LocalDate transactionDate;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Boolean isIncome;

    @Column(nullable = false)
    private String description;

    @Column
    private String label;

    @ManyToOne
    @JoinColumn
    private Wallet wallet;

}
