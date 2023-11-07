package org.example.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.example.data.data_source.UserDao;
import org.example.data.data_source.UserDaoImpl;
import org.example.data.repository.UserRepositoryImpl;
import org.example.domain.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.LinkedList;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();

        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }

    @Provides
    public Configuration provideHibernateConfiguration() {
        Configuration configuration = new Configuration();
        // Database connection settings for H2
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb"); // In-memory H2 database

        // Entity mappings
        configuration.addAnnotatedClass(User.class); // Add your User entity class

        // Other Hibernate properties
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "false");

        // Create a ServiceRegistry and MetadataSources
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class); // Add your User entity class

        return configuration;
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
