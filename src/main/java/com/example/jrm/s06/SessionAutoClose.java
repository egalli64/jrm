package com.example.jrm.s06;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionAutoClose {
    public static void main(String[] args) {
        // Auto close on SF makes sense here, just because this is a demo code
        try (SessionFactory sf = HibUtil.getSessionFactory(); // !!! UNCOMMON !!!
                Session session = sf.openSession()) {
            Region europe = session.find(Region.class, 1);
            System.out.println(europe);
        }
    }
}
