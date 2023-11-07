package org.example.data.data_source;

import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;

import java.util.List;

public interface UserDao {
    User createUser(@NeverNull User user);
    List<User> getAllUsers();
    void deleteAllUsers();
}
