package dao;

import db.ConnectionFactory;
import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartDaoImpl implements SubjectDao {
    @Override
    public List<Department> listSubjects() {

        List<Department> list = new ArrayList<>();

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement pst;
        ResultSet resultSet;

        try {
            connection = ConnectionFactory.getConnection();
            resultSet = connection.createStatement().executeQuery("select * from departments order by id");

            while (resultSet.next()) {
                Department subject = new Department();
                subject.setId(resultSet.getInt("id"));
                subject.setNumber(resultSet.getInt("numb"));
                subject.setName(resultSet.getString("title"));
                subject.setDescr(resultSet.getString("lecturer"));
            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
