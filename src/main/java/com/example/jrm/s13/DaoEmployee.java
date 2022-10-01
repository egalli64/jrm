package com.example.jrm.s13;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.Employee;
import com.example.jrm.dao.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class DaoEmployee {
    private static final Logger log = LoggerFactory.getLogger(DaoEmployee.class);

    @SuppressWarnings("unchecked")
    public List<Employee> getUntypedPayedTop(Double low) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "FROM Employee e WHERE e.salary > ?1 ORDER BY salary DESC";
            Query query = em.createQuery(jpql);
            query.setParameter(1, low);
            return (List<Employee>) query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getPayedTop(Double low) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "FROM Employee e WHERE e.salary > :low ORDER BY salary DESC";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("low", low);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getNamedPayedTop(Double low) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<Employee> query = em.createNamedQuery("getTopSalaried", Employee.class);
            query.setParameter("low", low);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Employee> getByName(String first, String last) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            String jpql = "FROM Employee e WHERE e.firstName = :first and e.lastName = :last";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("first", first);
            query.setParameter("last", last);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.debug("{} {}: {}", first, last, nre.getMessage());
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    public int deleteBetween(Integer low, Integer high) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            String jpql = "DELETE FROM Employee e WHERE e.id BETWEEN :low AND :high";
            Query query = em.createQuery(jpql);
            query.setParameter("low", low);
            query.setParameter("high", high);
            tx.begin();
            return query.executeUpdate();
        } catch (Exception ex) {
            log.error("Can't delete for {}, {}", low, ex);
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return 0;
        } finally {
            if (tx != null && tx.isActive()) {
                tx.commit();
            }
            em.close();
        }
    }
}
