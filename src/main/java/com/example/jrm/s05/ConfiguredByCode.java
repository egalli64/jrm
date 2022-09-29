package com.example.jrm.s05;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class ConfiguredByCode {
    public static void main(String[] args) {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        settings.put(Environment.USER, "hron");
        settings.put(Environment.PASS, "password");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

        Configuration configuration = new Configuration();
        configuration.setProperties(settings);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        System.out.println("Ready to work with Hibernate!");

        sessionFactory.close();
    }
}
