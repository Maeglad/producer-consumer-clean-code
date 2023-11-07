package org.example.data.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.Main;
import org.example.di.MainModule;
import org.example.di.TestModule;
import org.example.domain.model.User;
import org.example.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new TestModule(), new MainModule());
    }

    public void testAddNewUserAndGetAllUsersReturnsCorrectValues() {
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user = new User(
                1L, 2L, "username"
        );

        userRepository.addUser(user);
        List<User> userList = userRepository.getUsers();
        // check if returned value is correct
        assertEquals(userList.size(), 1);
        User returnedUser = userList.get(0);
        assertEquals(user.getUserId(), returnedUser.getUserId());
        assertEquals(user.getUserGuid(), returnedUser.getUserGuid());
        assertEquals(user.getUserName(), returnedUser.getUserName());
    }

    @Test
    public void testAddNewUserTwoTimes() {
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user1 = new User(
                1L, 11L, "111"
        );

        User user2 = new User(
                2L, 22L, "222"
        );
        // insert users
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        List<User> userList = userRepository.getUsers();
        assertEquals(userList.size(), 2);
    }

    @Test
    public void testAddUserReturnsCorrectValues() {
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user1 = new User(
                1L, 2L, "username"
        );

        User returnValue = userRepository.addUser(user1);
        // check if returned value is correct
        assertNotNull(returnValue);
        assertEquals(user1.getUserId(), returnValue.getUserId());
        assertEquals(user1.getUserGuid(), returnValue.getUserGuid());
        assertEquals(user1.getUserName(), returnValue.getUserName());
    }

    @Test
    public void testAddTwoNewUsersAndThenDeleteCheckCounts() {
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user1 = new User(
                1L, 11L, "111"
        );

        User user2 = new User(
                2L, 22L, "222"
        );
        // insert users
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        List<User> userList = userRepository.getUsers();
        assertEquals(userList.size(), 2);

        userRepository.deleteAllUsers();
        userList = userRepository.getUsers();
        assertTrue(userList.isEmpty());
    }
}