package com.example.jrm.s16;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class RegionDao {
    private static final String SELECT_ALL_REGIONS = "SELECT r FROM s16.Region r";
    private static final String EAGER_SELECT_ALL_REGIONS = "SELECT DISTINCT r FROM s16.Region r JOIN FETCH r.countries";

    public List<Region> readAllLazy() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(SELECT_ALL_REGIONS, Region.class).getResultList();
        }
    }

    public List<Region> readAllEager() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(EAGER_SELECT_ALL_REGIONS, Region.class).getResultList();
        }
    }
}
