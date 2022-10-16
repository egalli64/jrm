package com.example.jrm.s16;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class RegionEagerDao {
    public List<RegionEager> readAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT r FROM RegionEager r";
            return em.createQuery(jpql, RegionEager.class).getResultList();
        } finally {
            em.close();
        }
    }
}
