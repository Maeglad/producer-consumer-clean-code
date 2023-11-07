package org.example.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.data.data_source.MockUserDao;
import org.example.data.data_source.UserDao;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        bind(UserDao.class).to(MockUserDao.class);
    }

    @Provides
    @Singleton
    public MockUserDao provideMockUserDao() {
        return new MockUserDao();
    }
}
