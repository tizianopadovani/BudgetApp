package it.unikey.budgetapp.DAL.repositories;

import it.unikey.budgetapp.DAL.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    @Transactional
    void itShouldCheckIfUserExistsByEmail() {
        //given
        User user = new User();
        user.setFirstname("Mario");
        user.setLastname("Rossi");
        user.setEmail("mariorossi@mail.com");
        user.setPassword("1234567");
        user.setCreatedAt(LocalDate.now());
        underTest.getUserByEmail(user.getEmail());

        //when
        User exists = underTest.getUserByEmail(user.getEmail());

        //then
        User result = new User();
        result.setFirstname("Mario");
        result.setLastname("Rossi");
        result.setEmail("mariorossi@mail.com");
        result.setPassword("1234567");
        result.setCreatedAt(LocalDate.now());
        underTest.save(result);

        assertThat(exists.getEmail()).isEqualTo(result.getEmail());
    }
}