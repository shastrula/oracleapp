package com.aligny.oracleapp.dao;

import com.aligny.oracleapp.domain.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr", "hr");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from employees");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> employees = new ArrayList<Employee>();

        while(resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
            employee.setFirstName(resultSet.getString("FIRST_NAME"));
            employee.setLastName(resultSet.getString("LAST_NAME"));
            employees.add(employee);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        System.out.println(employees);
    }
}
