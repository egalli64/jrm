package com.example.jrm.s11;

import java.util.Optional;

import com.example.jrm.dao.JpaUtil;
import com.example.jrm.dao.Region;

public class FindRegion {
    public static void main(String[] args) {
        DaoRegion dao = new DaoRegion();

        for (int i = 1; i < 6; i++) {
            Optional<Region> opt = dao.read(i);
            opt.ifPresentOrElse(System.out::println, () -> {
                System.out.println("Not found");
            });
        }

        for (int i = 1; i < 6; i++) {
            Region region = dao.rawRead(i);
            if (region != null) {
                System.out.println(region);
            } else {
                System.out.println("Not found");
            }
        }

        System.out.println("Shutdown");
        JpaUtil.getEntityManagerFactory().close();
    }
}
