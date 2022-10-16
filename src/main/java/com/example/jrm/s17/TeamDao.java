package com.example.jrm.s17;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class TeamDao {
    public List<Team> readAllLazy() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT t FROM Team t";
            return em.createQuery(jpql, Team.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Team> readAllEager() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT DISTINCT t FROM Team t JOIN FETCH t.employees";
            return em.createQuery(jpql, Team.class).getResultList();
        } finally {
            em.close();
        }
    }
}
