package com.example.jrm.s17;

import java.util.List;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class TeamDao {
    private static final String SELECT_ALL_TEAMS = "SELECT t FROM Team t";
    private static final String EAGER_SELECT_ALL_TEAMS = "SELECT DISTINCT t FROM Team t JOIN FETCH t.employees";

    public List<Team> readAllLazy() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(SELECT_ALL_TEAMS, Team.class).getResultList();
        }
    }

    public List<Team> readAllEager() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(EAGER_SELECT_ALL_TEAMS, Team.class).getResultList();
        }
    }
}
