package com.example.jrm.s07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class FindEntity {
    public static void main(String[] args) {
        EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

        // each EM created should be closed
        try (EntityManager em = emf.createEntityManager()) {
            Region europe = em.find(Region.class, 1);
            System.out.println("The found region: " + europe);
        } finally {
            // just a single EMF is usually used in an application
            emf.close();
        }
    }
}
