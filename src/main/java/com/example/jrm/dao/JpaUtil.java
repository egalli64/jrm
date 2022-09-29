package com.example.jrm.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {
    private JpaUtil() {
    }

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("hron");

    public static EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }
}
