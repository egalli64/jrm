package com.example.jrm.s16;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.JpaUtil;

public class CrudManyToOne {
    static private final Logger log = LoggerFactory.getLogger(CrudManyToOne.class);

    private CountryDao countryDao;
    private RegionDao regionDao;
    private RegionEagerDao regionEagerDao;

    public CrudManyToOne() {
        countryDao = new CountryDao();
        regionDao = new RegionDao();
        regionEagerDao = new RegionEagerDao();
    }

    public void printAllCountries() {
        List<Country> countries = countryDao.readAll();
        System.out.println("All countries:");
        countries.forEach(System.out::println);
        System.out.println("---");
    }

    public void lazyPrintAllRegions() {
        List<Region> regions = regionDao.readAllLazy();
        System.out.println("All regions:");
        for (Region region : regions) {
            System.out.println(region.getId() + " " + region.getName());
            Set<Country> countries = region.getCountries();
            if (Hibernate.isInitialized(countries)) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, country set is un unusable proxy");
            }
        }
        System.out.println("---");
    }

    public void printAllRegionsJoinFetch() {
        List<Region> regions = regionDao.readAllEager();
        System.out.println("All regions (by eager query):");
        regions.forEach(System.out::println);
        System.out.println("---");
    }

    public void eagerPrintAllRegions() {
        List<RegionEager> regions = regionEagerDao.readAll();
        System.out.println("All regions (by eager entity):");
        regions.forEach(System.out::println);
        System.out.println("---");
    }

    public static void main(String[] args) {
        try {
            CrudManyToOne crud = new CrudManyToOne();
            crud.printAllCountries();

            crud.lazyPrintAllRegions();
            crud.printAllRegionsJoinFetch();

            crud.eagerPrintAllRegions();
        } finally {
            System.out.println("Shutdown");
            JpaUtil.getEntityManagerFactory().close();
        }
    }
}
