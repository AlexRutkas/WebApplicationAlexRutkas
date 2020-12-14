package servlets.jdbc;

import db.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ReadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        Connection connection = ConnectionFactory.getConnection();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");//table departmens
            while (resultSet.next()) {

                pw.println(resultSet.getString("name")+" - "+resultSet.getString("descr"));

            }
            statement.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
