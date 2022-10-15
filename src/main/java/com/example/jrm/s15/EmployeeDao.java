package com.example.jrm.s15;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class EmployeeDao {
    static private final Logger log = LoggerFactory.getLogger(EmployeeDao.class);

    public List<Employee> readAll() {
        log.trace("readAll");

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "SELECT e FROM s15.Employee e";
            return em.createQuery(jpql, Employee.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Employee> read(int id) {
        log.trace("read({})", id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            return Optional.ofNullable(em.find(Employee.class, id));
        } finally {
            em.close();
        }
    }
}
