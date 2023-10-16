DROP TABLE IF EXISTS employ;

CREATE TABLE employ (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(255),
    emp_salary INT,
    role VARCHAR(255)
);
