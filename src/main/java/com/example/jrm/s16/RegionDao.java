package com.example.jrm.s16;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class RegionDao {
    public List<Region> readAllLazy() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT r FROM s16.Region r";
            return em.createQuery(jpql, Region.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Region> readAllEager() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT DISTINCT r FROM s16.Region r JOIN FETCH r.countries";
            return em.createQuery(jpql, Region.class).getResultList();
        } finally {
            em.close();
        }
    }
}
