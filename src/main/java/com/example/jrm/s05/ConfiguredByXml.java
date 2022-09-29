package com.example.jrm.s05;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfiguredByXml {
    public static void main(String[] args) {
        Configuration conf = new Configuration().configure("s05/minimal.xml");

        SessionFactory sf = conf.buildSessionFactory();

        System.out.println("Ready to work with Hibernate!");

        sf.close();
    }
}
