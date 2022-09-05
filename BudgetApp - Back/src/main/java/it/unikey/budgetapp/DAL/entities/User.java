package it.unikey.budgetapp.DAL.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "[user]")

public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "[user_id]")
    private List<Wallet> walletList;
}
