package org.example.data.data_source;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@Singleton
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Inject
    public UserDaoImpl(@NeverNull SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User createUser(@NeverNull User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        return user;
    }

    @Override
    @NeverNull
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void deleteAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        }
    }
}
