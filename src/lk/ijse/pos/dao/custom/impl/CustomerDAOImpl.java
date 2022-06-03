package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (cusId,cusName, CusAddress,cusNic,cusPhone) VALUES (?,?,?,?,?)", entity.getId(), entity.getName(), entity.getAddress(),entity.getNic(),entity.getPhone());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?", entity.getName(), entity.getAddress(), entity.getId());
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE CusID=?", s);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5) );
        }else {
            return null;
        }

    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT cusId FROM Customer ORDER BY cusId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("cusId");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

}
