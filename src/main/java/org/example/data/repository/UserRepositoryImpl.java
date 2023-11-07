package org.example.data.repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.data.data_source.UserDao;
import org.example.domain.model.User;
import org.example.domain.repository.UserRepository;

import java.util.List;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    @Inject
    public UserRepositoryImpl(@NeverNull UserDao userDao) {
        this.userDao = userDao;
    }

    @NeverNull
    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    @NeverNull
    @Override
    public User addUser(@NeverNull User newUser) {
        return userDao.createUser(newUser);
    }
}
