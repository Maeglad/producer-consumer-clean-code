package org.example.data.data_source;

import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class MockUserDao implements UserDao {

    private final List<User> userDatabase = new ArrayList<>();

    @Override
    @NeverNull
    public User createUser(@NeverNull User user) {
        userDatabase.add(user);
        return user;
    }

    @Override
    @NeverNull
    public List<User> getAllUsers() {
        return new ArrayList<>(userDatabase);
    }

    @Override
    public void deleteAllUsers() {
        userDatabase.clear();
    }
}
