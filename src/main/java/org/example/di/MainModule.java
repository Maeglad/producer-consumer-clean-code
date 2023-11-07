package org.example.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.example.data.data_source.UserDao;
import org.example.data.data_source.UserDaoImpl;
import org.example.data.repository.UserRepositoryImpl;
import org.example.domain.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();

        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }

    @Provides
    public Configuration provideConfiguration() {
        return new Configuration().configure("hibernate.cfg.xml");
    }

    @Provides
    public SessionFactory provideSessionFactory(Configuration configuration) {
        return configuration.buildSessionFactory();
    }

    @Provides
    public UserRepositoryImpl provideUserRepositoryImpl(UserDao userDao) {
        return new UserRepositoryImpl(userDao);
    }
}
