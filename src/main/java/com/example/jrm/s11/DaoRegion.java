package com.example.jrm.s11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.JpaUtil;
import com.example.jrm.dao.Region;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DaoRegion {
    private static final Logger log = LoggerFactory.getLogger(DaoRegion.class);

    public Region read(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Region region = em.find(Region.class, id);
        em.close();

        return region;
    }

    public Region create(Region region) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(region);
            tx.commit();
            return region;
        } catch (Exception ex) {
            log.error("Can't persist entity", ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return null;
        } finally {
            em.close();
        }
    }

    public boolean update(Region region) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(region);
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't merge entity", ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean delete(Region entity) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't remove " + entity, ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return false;
        } finally {
            em.close();
        }
    }

    public Region delete(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            Region entity = em.find(Region.class, id);
            if (entity != null) {
                tx = em.getTransaction();
                tx.begin();
                em.remove(entity);
                tx.commit();
                return entity;
            } else {
                log.info("Can't remove missing entity " + id);
                return null;
            }
        } catch (Exception ex) {
            log.warn("Can't remove entity " + id, ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return null;
        } finally {
            em.close();
        }
    }
}
