package hu.nye.progkor.employee.employeeCatalog.controller;


import hu.nye.progkor.employee.employeeCatalog.model.Employee;
import hu.nye.progkor.employee.employeeCatalog.model.exception.NotFoundException;
import hu.nye.progkor.employee.employeeCatalog.service.EmployeeCatalogService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CRUD MŰVELETEK

@Controller
@RequestMapping("/employee")
public class EmployeeCatalogController {


  private final EmployeeCatalogService employeeCatalogService;

  public EmployeeCatalogController(EmployeeCatalogService employeeCatalogService) {
    this.employeeCatalogService = employeeCatalogService;
  }

  @GetMapping
  public String getAllEmployee(final Model model) {
    final List<Employee> employees = employeeCatalogService.getAllEmployees();
    model.addAttribute("employees", employees);
    return "employee/list";

  }

  @GetMapping("/{id}")
  public String getEmployee(final Model model, final @PathVariable Long id) {
    final Employee employee = employeeCatalogService.getEmployee(id);
    model.addAttribute("employee", employee);
    return "employee/edit";

  }


  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String createEmployee(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final Employee employeeChanges) {
    final Employee employee = employeeCatalogService.updateEmployee(id, employeeChanges);
    model.addAttribute("employee", employee);
    return "employee/edit";

  }

  @GetMapping("/{id}/delete")
  public String deleteEmployee(final Model model, @PathVariable("id") Long id) {
    try {
      employeeCatalogService.deleteEmployee(id);
    } catch (NotFoundException e) {

    }
    final List<Employee> employees = employeeCatalogService.getAllEmployees();
    model.addAttribute("employees", employees);
    return "employee/list";

  }


  @GetMapping("/create")
  public String createEmployeeForm(final Model model) {
    return "employee/create";
  }

  @PostMapping("/create")
  public String createEmployee(final Model model, final Employee employee) {
    final Employee saveEmployee = employeeCatalogService.createEmployee(employee);
    model.addAttribute("employee", saveEmployee);
    return "employee/edit";
  }

}
