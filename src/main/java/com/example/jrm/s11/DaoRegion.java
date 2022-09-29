package com.example.jrm.s11;

import java.util.Optional;

import com.example.jrm.dao.JpaUtil;
import com.example.jrm.dao.Region;

import jakarta.persistence.EntityManager;

public class DaoRegion {
    public Optional<Region> read(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Region region = em.find(Region.class, id);
        em.close();

        return Optional.ofNullable(region);
    }

    public Region rawRead(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Region region = em.find(Region.class, id);
        em.close();

        return region;
    }
}
