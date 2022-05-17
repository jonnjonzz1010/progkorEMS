package hu.nye.progkor.employee.employeeCatalog.service.impl;


import hu.nye.progkor.employee.employeeCatalog.model.Departments;
import hu.nye.progkor.employee.employeeCatalog.model.Employee;
import hu.nye.progkor.employee.employeeCatalog.model.Gender;
import hu.nye.progkor.employee.employeeCatalog.model.exception.NotFoundException;
import hu.nye.progkor.employee.employeeCatalog.service.EmployeeCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeCatalogServiceImpl implements EmployeeCatalogService {

  private final List<Employee> dataBase = new ArrayList<>();

  @Autowired
  public EmployeeCatalogServiceImpl() {
    dataBase.add(new Employee(1L, "Dominik Varga", Gender.MALE, Departments.NYIREGYHAZA));
    dataBase.add(new Employee(2L, "Kis Katalin", Gender.MALE, Departments.WIEN));

  }

  public EmployeeCatalogServiceImpl(final List<Employee> employees) {
    dataBase.addAll(employees);
  }

  @Override
  public List<Employee> getAllEmployees() {
    return Collections.unmodifiableList(dataBase);
  }


  @Override
  public Employee getEmployee(final Long id) {
    return dataBase.stream()
            .filter(employee -> employee.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);

  }

  @Override
  public Employee createEmployee(final Employee employee) {
    employee.setId(getNextId());
    dataBase.add(employee);
    return employee;


  }

  @Override
  public Employee updateEmployee(final Long id, final Employee employeeChange) {
    final Employee employee = getEmployee(id);
    employee.setName(employee.getName());
    employee.setDepartments(employee.getDepartments());
    employee.setGender(employee.getGender());
    return employee;

  }


  @Override
  public void deleteEmployee(final Long id) {
    final Employee employee = getEmployee(id);
    dataBase.remove(employee);


  }

  private Long getNextId() {
    return getLastId() + 1L;

  }

  private Long getLastId() {
    return dataBase.stream()
            .mapToLong(Employee::getId)
            .max()
            .orElse(0);


  }

}
