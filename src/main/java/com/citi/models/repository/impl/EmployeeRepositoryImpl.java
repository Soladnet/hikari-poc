package com.citi.models.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.models.Employee;
import com.citi.models.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
 
	@Override
	public void createEmployee(Employee employee) {
		String SQL = "INSERT INTO employee_table(employee_name ,email,gender,salary) VALUES (?,?,?,?)";
		int update = getJdbcTemplate().update(SQL, employee.getEmployeeName(),employee.getEmail(),employee.getGender(),employee.getSalary());
		if(update == 1) {
			System.out.println("Employee is created..");
		}
	}
 
	@Override
	public Employee fetchEmployeeById(Integer employeeId) {
		
		String SQL = "SELECT * FROM employee_table WHERE employee_id = ?";
		// try {
			return (Employee) getJdbcTemplate().queryForObject(SQL, new EmployeeRowMapper(), employeeId);
		// } catch (Exception e) {
		// 	return null;
		// }
		
	}
	@Override
	public List<Employee> fetchAllEmployees() {
		String SQL = "SELECT * FROM employee_table";
		return getJdbcTemplate().query(SQL, new EmployeeRowMapper());
	}
	@Override
	public void updateEmployeeEmailById(String newEmail, Integer employeeId) {
		String SQL = "UPDATE employee_table set email = ? WHERE employee_id = ?";
		int update = getJdbcTemplate().update(SQL, newEmail,employeeId);
		if(update == 1) {
			System.out.println("Employee email is updated for ID = "+employeeId);
		}
	}
	@Override
	public void deleteEmployeeById(Integer employeeId) {
		String SQL = "DELETE FROM employee_table WHERE employee_id = ?";
		int update = getJdbcTemplate().update(SQL,employeeId);
		if(update == 1) {
			System.out.println("Employee is deleted with ID = "+employeeId);
		}
	}
    
}
