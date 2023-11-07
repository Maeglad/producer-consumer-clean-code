package org.example.domain.repository;

import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;

import java.util.List;

public interface UserRepository {

    /**
     * @return all user entities in database
     */
    @NeverNull
    List<User> getUsers();

    /**
     * Delete all user entities in database
     */
    void deleteAllUsers();

    /**
     * Persist new user in database
     * @param newUser id may be null
     * @return {@link User} entity that was persisted in database.
     *          Some fields may have been updated during persist operation.
     */
    @NeverNull
    User addUser(@NeverNull User newUser);
}


