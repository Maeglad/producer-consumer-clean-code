package org.example.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.data.data_source.UserDao;
import org.example.data.data_source.UserDaoImpl;
import org.hibernate.SessionFactory;

public class DaoModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();

        bind(UserDao.class).to(UserDaoImpl.class);
    }

    @Provides
    @Singleton
    public UserDaoImpl provideUserDao(SessionFactory sessionFactory) {
        return new UserDaoImpl(sessionFactory);
    }
}
