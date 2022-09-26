package it.unikey.budgetapp.BLL.service.implementation;

import it.unikey.budgetapp.BLL.service.abstraction.UserService;
import it.unikey.budgetapp.DAL.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl underTest;

    @Test
    @Disabled
    void loadUserByUsername() {
    }

    @Test
    @Disabled
    void saveUser() {
    }

    @Test
    void findAllUsers() {
        //when
        underTest.findAllUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void findUserById() {
    }

    @Test
    @Disabled
    void findUserByEmail() {
    }

    @Test
    @Disabled
    void deleteUserById() {
    }

    @Test
    @Disabled
    void patchUserById() {
    }
}