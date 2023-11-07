package org.example.data.data_source;

import com.google.inject.Inject;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {
    private final SessionFactory sessionFactory;

    @Inject
    public UserDao(@NeverNull SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User createUser(@NeverNull User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        return user;
    }

    @NeverNull
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    public void deleteAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User", User.class).executeUpdate();
            transaction.commit();
        }
    }
}
