package it.unikey.budgetapp.DAL.repositories;

import it.unikey.budgetapp.DAL.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query
    User getUserByEmail(String email);

}
