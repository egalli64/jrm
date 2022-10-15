package com.example.jrm.s15;

import java.util.List;
import java.util.Optional;

public class CrudOneToOne {
    private CarDao carDao;
    private EmployeeDao empDao;

    public CrudOneToOne() {
        carDao = new CarDao();
        empDao = new EmployeeDao();
    }

    public void printAllCars() {
        List<Car> cars = carDao.readAll();
        System.out.println("All cars:");
        cars.forEach(System.out::println);
        System.out.println("---");
    }

    public void printCar(int id) {
        Optional<Car> car = carDao.read(id);
        car.ifPresentOrElse(System.out::println, () -> System.out.println("Car " + id + " not found"));
        System.out.println("---");
    }

    public void printAllEmployeesWithCar() {
        List<Employee> employees = empDao.readAll();
        System.out.println("All employees with car:");
        for (Employee emp : employees) {
            Car car = emp.getCar();
            if (car != null) {
                System.out.print(emp);
                System.out.println(" -> " + car.getName());
            }
        }

        System.out.println("---");
    }

    public void printEmployeeWithCar(int id) {
        Optional<Employee> opt = empDao.read(id);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            System.out.print(emp + " -> ");
            Car car = emp.getCar();
            System.out.println(car == null ? "<none>" : car.getName());
        }
        System.out.println("---");
    }

    public static void main(String[] args) {
        CrudOneToOne crud = new CrudOneToOne();
        crud.printAllCars();
        crud.printCar(12);
        crud.printCar(42);

        crud.printAllEmployeesWithCar();
        crud.printEmployeeWithCar(120);
        crud.printEmployeeWithCar(165);
    }
}
