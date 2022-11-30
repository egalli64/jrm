package com.example.jrm.s13;

import java.util.List;
import java.util.Optional;

import com.example.jrm.dao.Employee;
import com.example.jrm.dao.JpaUtil;

public class CrudEmployee {
    private DaoEmployee dao;

    public CrudEmployee() {
        dao = new DaoEmployee();
    }

    public void untypedPrintTopPayed(double lower) {
        List<Employee> employees = dao.getUntypedPayedTop(lower);
        System.out.println("Employees payed more than " + lower);
        employees.forEach(System.out::println);
        System.out.println("---");
    }

    public void printTopPayed(double lower) {
        List<Employee> employees = dao.getPayedTop(lower);
        System.out.println("Employees payed more than " + lower);
        employees.forEach(System.out::println);
        System.out.println("---");
    }

    public void namedPrintTopPayed(double lower) {
        List<Employee> employees = dao.getNamedPayedTop(lower);
        System.out.println("Employees payed more than " + lower);
        employees.forEach(System.out::println);
        System.out.println("---");
    }

    public void printByName(String first, String last) {
        Optional<Employee> employee = dao.getByName(first, last);
        System.out.println("Details on a single employee");
        employee.ifPresentOrElse(System.out::println, () -> System.out.println(first + " " + last + " not found"));
    }

    public void deleteRange(int low, int high) {
        int result = dao.deleteBetween(low, high);
        System.out.println("Deleted " + result + " employees");
    }

    public static void main(String[] args) {
        try {
            CrudEmployee crud = new CrudEmployee();
            crud.untypedPrintTopPayed(13_000.0);
            crud.printTopPayed(15_000.0);
            crud.namedPrintTopPayed(20_000.0);

            crud.printByName("Stephen", "King");
            crud.printByName("Steven", "King");

            crud.deleteRange(1_000, 1_010);
        } finally {
            System.out.println("Shutdown");
            JpaUtil.getEntityManagerFactory().close();
        }
    }
}
