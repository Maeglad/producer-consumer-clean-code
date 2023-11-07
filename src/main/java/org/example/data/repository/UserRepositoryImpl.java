package org.example.data.repository;

import com.google.inject.Inject;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.data.data_source.UserDao;
import org.example.domain.model.User;
import org.example.domain.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    public UserRepositoryImpl() {
    }

    @NeverNull
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void deleteAllUsers() {

    }

    @NeverNull
    @Override
    public User addUser(@NeverNull User newUser) {
        return null;
    }
}
