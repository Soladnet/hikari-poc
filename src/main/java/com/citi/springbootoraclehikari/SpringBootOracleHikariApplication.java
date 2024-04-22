package com.citi.springbootoraclehikari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.citi.models.Employee;
import com.citi.models.repository.EmployeeRepository;
import com.citi.models.repository.impl.EmployeeRepositoryImpl;

@SpringBootApplication
@ComponentScan("com.citi.models.repository.impl")
public class SpringBootOracleHikariApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootOracleHikariApplication.class, args);

        EmployeeRepository employeeDao =  applicationContext.getBean(EmployeeRepositoryImpl.class);
		//employeeDao.createEmployee(getEmployee());
		Employee employee = employeeDao.fetchEmployeeById(1);
		System.out.println("---------------------------------------");
		employeeDao.fetchAllEmployees().forEach(System.out::println);
		System.out.println("---------------------------------------");
    }

}
