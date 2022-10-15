package com.example.jrm.s15;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class CarDao {
    static private final Logger log = LoggerFactory.getLogger(CarDao.class);

    public List<Car> readAll() {
        log.trace("readAll");

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT c FROM s15.Car c";
            return em.createQuery(jpql, Car.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Car> read(int id) {
        log.trace("read({})", id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return Optional.ofNullable(em.find(Car.class, id));
        } finally {
            em.close();
        }
    }
}
