package com.example.jrm.s17;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrm.dao.JpaUtil;

public class CrudManyToMany {
    static private final Logger log = LoggerFactory.getLogger(CrudManyToMany.class);

    private TeamDao teamDao;

    public CrudManyToMany() {
        teamDao = new TeamDao();
    }

    public void lazyPrintAllTeams() {
        List<Team> teams = teamDao.readAllLazy();
        System.out.println("All teams:");
        for (Team team : teams) {
            System.out.println(team.getId() + " " + team.getName());
            Set<Employee> employees = team.getEmployees();
            if (Hibernate.isInitialized(employees)) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, employee set is un unusable proxy");
            }
        }
        System.out.println("---");
    }

    public void printAllTeamsJoinFetch() {
        List<Team> teams = teamDao.readAllEager();
        System.out.println("All teams (by eager query):");
        teams.forEach(System.out::println);
        System.out.println("---");
    }

    public static void main(String[] args) {
        CrudManyToMany crud = new CrudManyToMany();
        crud.lazyPrintAllTeams();
        crud.printAllTeamsJoinFetch();

        System.out.println("Shutdown");
        JpaUtil.getEntityManagerFactory().close();
    }
}
