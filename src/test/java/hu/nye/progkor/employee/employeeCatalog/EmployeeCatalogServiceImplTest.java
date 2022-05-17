package hu.nye.progkor.employee.employeeCatalog;


import hu.nye.progkor.employee.employeeCatalog.model.Departments;
import hu.nye.progkor.employee.employeeCatalog.model.Employee;
import hu.nye.progkor.employee.employeeCatalog.model.Gender;
import hu.nye.progkor.employee.employeeCatalog.model.exception.NotFoundException;
import hu.nye.progkor.employee.employeeCatalog.service.EmployeeCatalogService;
import hu.nye.progkor.employee.employeeCatalog.service.impl.EmployeeCatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeCatalogServiceImplTest {

    private static final Employee VARGA_DOMINIK_EMPLOYEE = new Employee(1L, "Varga Dominik", Gender.MALE, Departments.NYIREGYHAZA);
    private static final Employee KIS_KATALIN_EMPLOYEE = new Employee(2L, "Kis Katalin", Gender.FEMALE, Departments.WIEN);
    private static final List<Employee> EMPLOYEES = List.of(
            VARGA_DOMINIK_EMPLOYEE,
            KIS_KATALIN_EMPLOYEE
    );



    public static final long UNKNOWN_EMPLOYEE_ID = -1L;
    public static final String ELON_MUSK_EMPLOYEE_NAME = "Elon Musk";
    public static final String VARGA_DOMINIK_NAME = "Varga Dominik";

    private EmployeeCatalogService underTest;


    @BeforeEach
    void setup(){underTest = new EmployeeCatalogServiceImpl(EMPLOYEES);
    }


    @Test
    void getEmployeeShouldReturnAllEmployees(){
        final List<Employee> actual = underTest.getAllEmployees();
        assertThat(actual).isEqualTo(EMPLOYEES);


    }

    @Test
    void getEmployeeShouldReturnEmployeeWhenGivenIdOfExistingEmployee(){
        final Employee actual = underTest.getEmployee(KIS_KATALIN_EMPLOYEE.getId());
        assertThat(actual).isEqualTo(KIS_KATALIN_EMPLOYEE);

    }

    @Test
    void getEmployeeShouldReturnEmployeeWhenGivenIdOfExistingEmployee2(){
        final Employee actual = underTest.getEmployee(VARGA_DOMINIK_EMPLOYEE.getId());
        assertThat(actual).isEqualTo(VARGA_DOMINIK_EMPLOYEE);

    }

    @Test
    void getEmployeeShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingEmployee() {
        // when then
        assertThrows(NotFoundException.class, () -> underTest.getEmployee(UNKNOWN_EMPLOYEE_ID));
    }

    @Test
    void createEmployeeShouldReturnEmployeeWhenDelegateIt() {
        // given
        final Employee elonMuskEmployee = new Employee(null, ELON_MUSK_EMPLOYEE_NAME, Gender.MALE, Departments.BUDAPEST);
        final Employee expectedElonMuskEmployee = new Employee(3L, ELON_MUSK_EMPLOYEE_NAME, Gender.MALE, Departments.BUDAPEST);
        // when
        final Employee actual = underTest.createEmployee(elonMuskEmployee);
        // then
        assertThat(actual).isEqualTo(expectedElonMuskEmployee);
    }

/*    @Test
    void updateEmployeeShouldReturnUpdatedEmployeeWhenGivenIdOfExistingEmployee() {
        // given
        final Employee elonMuskEmployee = new Employee(null, ELON_MUSK_EMPLOYEE_NAME, Gender.MALE, Departments.BUDAPEST);
        final Employee expectedElonMuskEmployee = new Employee(KIS_KATALIN_EMPLOYEE.getId(),ELON_MUSK_EMPLOYEE_NAME, Gender.FEMALE, Departments.WIEN);
        // when
        final Employee actual = underTest.updateEmployee(KIS_KATALIN_EMPLOYEE.getId(), elonMuskEmployee);
        // then
        assertThat(actual).isEqualTo(elonMuskEmployee);
    }*/


    @Test
    void updateEmployeeShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingEmployee() {
        // given
        final Employee elonMuskEmployee = new Employee(null, ELON_MUSK_EMPLOYEE_NAME, Gender.MALE, Departments.BUDAPEST);
        // when then
        assertThrows(NotFoundException.class, () -> underTest.updateEmployee(UNKNOWN_EMPLOYEE_ID, elonMuskEmployee));
    }

    @Test
    void updateEmployeeShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingEmployee2() {
        // given
        final Employee vargaDominikEmployee = new Employee(null, VARGA_DOMINIK_NAME, Gender.MALE, Departments.NYIREGYHAZA);
        // when then
        assertThrows(NotFoundException.class, () -> underTest.updateEmployee(UNKNOWN_EMPLOYEE_ID, vargaDominikEmployee));
    }




    @Test
    void deleteRolePlayShouldDeleteRolePlayWhenGivenIdOfRolePlay() {
        // given
        final List<Employee> expectedEmployees = List.of(KIS_KATALIN_EMPLOYEE);
        // when
        underTest.deleteEmployee(VARGA_DOMINIK_EMPLOYEE.getId());
        final List<Employee> actual = underTest.getAllEmployees();
        // then
        assertThat(actual).isEqualTo(expectedEmployees);
    }


}
