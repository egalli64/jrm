package com.example.jrm.s11;

import com.example.jrm.dao.JpaUtil;
import com.example.jrm.dao.Region;

public class CrudRegion {
    private DaoRegion dao;

    public CrudRegion() {
        dao = new DaoRegion();
    }

    public void printRegionsById(int begin, int end) {
        for (int i = begin; i < end; i++) {
            Region region = dao.read(i);
            System.out.println(region != null ? region : "Not found");
        }
    }

    public Region create(String name) {
        Region region = new Region(name);
        return dao.create(region);
    }

    public boolean update(Region region, String name) {
        region.setName(name);
        return dao.update(region);
    }

    public boolean delete(Region region) {
        return dao.delete(region);
    }

    public Region delete(int id) {
        return dao.delete(id);
    }

    public static void main(String[] args) {
        CrudRegion crud = new CrudRegion();
        crud.printRegionsById(1, 6);

        Region region = crud.create("Oceania");
        System.out.println(region != null ? region : "Can't create new region");

        if (region != null) {
            if (crud.update(region, "Antartica")) {
                System.out.println("Region updated " + region);
            }

            if (crud.delete(region)) {
                System.out.println("Region deleted " + region);
            }
        }

        region = crud.delete(99);
        System.out.println("Region deleted (or null) " + region);

        System.out.println("Shutdown");
        JpaUtil.getEntityManagerFactory().close();
    }
}
