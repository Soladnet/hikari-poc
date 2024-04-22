DROP TABLE IF EXISTS employee_table;

-- Create the employee_table
CREATE TABLE IF NOT EXISTS employee_table (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    salary DECIMAL(10, 2)
);
