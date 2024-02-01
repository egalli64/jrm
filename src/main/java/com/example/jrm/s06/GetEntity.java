package com.example.jrm.s06;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GetEntity {
    public static void main(String[] args) {
        SessionFactory sf = HibUtil.getSessionFactory();
        Session session = sf.openSession();

        Region missing = session.get(Region.class, 42);
        System.out.println("A missing region: " + missing);

        session.close();

        // Closing the SF makes sense just because this is a demo code
        sf.close();
    }
}
