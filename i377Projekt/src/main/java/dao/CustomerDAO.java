package dao;

import models.Customer;
import util.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Used sources: https://www.youtube.com/watch?v=cdHi1ZRy6tE&feature=youtu.be

public class CustomerDAO {

    public static String URL = "jdbc:hsqldb:mem:db1";

    public static List<Customer> getCustomers() {

        List<Customer> customers= new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = conn.createStatement()) {

            try (ResultSet r = stmt.executeQuery("select * FROM  customer")) {
                while (r.next()) {

                    customers.add(new Customer(r.getLong(1),r.getString(2),r.getString(3),r.getString(4)));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;

    }//getCustomers

    public static Customer getCustomerById(Long id){

        String sql = "SELECT id, firstName, lastName, code FROM customer WHERE id = ?";

        Customer customer = null;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setLong(1, id);

            try (ResultSet resultSet = pst.executeQuery()) {
                while (resultSet.next()) {
                    customer = new Customer(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customer;

    }//getCustomerById

    public static void addCustomer(Customer customer){

        String query = "INSERT INTO customer (id, firstName, lastName ,code)VALUES (NEXT VALUE FOR seq1, ?,?,?)";


        try (Connection conn = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1,customer.getFirstName());
                ps.setString(2,customer.getLastName());
                ps.setString(3,customer.getCode());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteCustomers(){

        try(Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement()){
            stmt.executeUpdate("TRUNCATE SCHEMA public AND COMMIT");
        } catch (SQLException e){
            throw new RuntimeException();
        }

    }//deleteCustomers

    public static void deleteCustomerById(Long id){
        String sql = "delete from customer where id = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setLong(1, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//deleteCustomersById
}