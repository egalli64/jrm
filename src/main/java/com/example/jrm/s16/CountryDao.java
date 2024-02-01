package com.example.jrm.s16;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class CountryDao {
    static private final String SELECT_ALL_COUNTRIES = "SELECT c FROM s16.Country c";

    public List<Country> readAll() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(SELECT_ALL_COUNTRIES, Country.class).getResultList();
        }
    }
}
