package com.example.jrm.s15;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class EmployeeDao {
    static private final Logger log = LoggerFactory.getLogger(EmployeeDao.class);
    static private final String SELECT_ALL_EMPLOYEES = "SELECT e FROM s15.Employee e";

    public List<Employee> readAll() {
        log.trace("readAll");

        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(SELECT_ALL_EMPLOYEES, Employee.class).getResultList();
        }
    }

    public Optional<Employee> read(int id) {
        log.trace("read({})", id);

        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return Optional.ofNullable(em.find(Employee.class, id));
        }
    }
}
