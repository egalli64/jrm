package com.example.jrm.s16;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class RegionEagerDao {
    static private final String SELECT_ALL_REGIONS = "SELECT r FROM RegionEager r";

    public List<RegionEager> readAll() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(SELECT_ALL_REGIONS, RegionEager.class).getResultList();
        }
    }
}
