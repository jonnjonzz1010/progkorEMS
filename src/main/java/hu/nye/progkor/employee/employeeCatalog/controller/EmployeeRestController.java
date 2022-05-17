package hu.nye.progkor.employee.employeeCatalog.controller;



import hu.nye.progkor.employee.employeeCatalog.model.Employee;
import hu.nye.progkor.employee.employeeCatalog.model.exception.NotFoundException;
import hu.nye.progkor.employee.employeeCatalog.service.EmployeeCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeRestController {

  private final EmployeeCatalogService employeeCatalogService;

  public EmployeeRestController(EmployeeCatalogService employeeCatalogService) {
    this.employeeCatalogService = employeeCatalogService;
  }

  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeCatalogService.getAllEmployees();
  }

  @GetMapping("/{id}")
  Employee getEmployee(@PathVariable("id") final Long id) {
    return employeeCatalogService.getEmployee(id);

  }

  @PostMapping
  Employee creaEmployee(final @RequestBody Employee employee) {
    return employeeCatalogService.createEmployee(employee);


  }

  @PutMapping
  Employee updateEmployee(final @PathVariable Long id, final @RequestBody Employee employeeChange) {
    return employeeCatalogService.updateEmployee(id, employeeChange);

  }


  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteEmployee(final @PathVariable Long id) {
    try {
      employeeCatalogService.deleteEmployee(id);
    } catch (NotFoundException e) {

    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
