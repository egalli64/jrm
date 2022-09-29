package com.example.jrm.s06;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibUtil {
    private static SessionFactory sessionFactory;

    private HibUtil() {
    }

    static {
        Configuration conf = new Configuration().configure("s06/class-mapping.xml");
        sessionFactory = conf.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
