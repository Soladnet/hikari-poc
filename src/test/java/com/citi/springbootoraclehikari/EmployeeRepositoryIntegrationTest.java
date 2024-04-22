package com.citi.springbootoraclehikari;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.citi.models.Employee;
import com.citi.models.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Sql({ "/schema-test.sql" })
public class EmployeeRepositoryIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateEmployee() {
        // Given
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("John Doe");
        employee.setEmail("john@example.com");

        // When
        employeeRepository.createEmployee(employee);

        // Then
        assertEquals(1, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee_table", Integer.class));
    }

    @Test
    public void testFetchEmployeeById() throws InterruptedException {

        // Given
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("John Doe");
        employee.setEmail("john@example.com");
        employeeRepository.createEmployee(employee);

        // When
        Employee fetchedEmployee = employeeRepository.fetchEmployeeById(1);

        // Then
        assertNotNull(fetchedEmployee);
        assertEquals("John Doe", fetchedEmployee.getEmployeeName());
        assertEquals("john@example.com", fetchedEmployee.getEmail());
    }
}
