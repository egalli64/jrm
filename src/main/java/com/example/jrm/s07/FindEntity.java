package com.example.jrm.s07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class FindEntity {
    public static void main(String[] args) {
        EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            Region europe = em.find(Region.class, 1);
            System.out.println(europe);
        } finally {
            // each EM created should be closed
            em.close();

            // just a single EMF is usually used in an application
            emf.close();
        }
    }
}
