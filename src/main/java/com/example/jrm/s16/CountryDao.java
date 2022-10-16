package com.example.jrm.s16;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class CountryDao {
    public List<Country> readAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT c FROM s16.Country c";
            return em.createQuery(jpql, Country.class).getResultList();
        } finally {
            em.close();
        }
    }
}
