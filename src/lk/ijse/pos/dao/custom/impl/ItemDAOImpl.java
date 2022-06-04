package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4),rst.getInt(5)));

        }
        return allItems;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Item (ItemCode,ItemName, description, unitPrice, qty) VALUES (?,?,?,?,?)", entity.getCode(),entity.getName(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Item SET ItemName=?, description=?, unitPrice=?, qty=? WHERE itemCode=?", entity.getName(),entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getCode());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", s);
        if (rst.next()) {
            return new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4),rst.getInt(5));
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        /*ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }*/

        String table ="Item";
        String incrementID=null;

        ResultSet maxId = SQLUtil.execute("SELECT CONCAT(MAX(0+SUBSTRING(ItemCode,3))) FROM Item");

        String id=null;

        while (maxId.next()){
            id=maxId.getString(1);
        }

        if (id!=null){
            int nextID = Integer.parseInt(id);
            nextID++;
            String cptl =table.substring(1,2);
            char v=cptl.charAt(0);
            char second = Character.toUpperCase(v);
            String first =table.substring(0,1);
            incrementID = first+second+nextID;

        }else {
            String cptl =table.substring(1,2);
            char v=cptl.charAt(0);
            char second = Character.toUpperCase(v);
            String first =table.substring(0,1);
            incrementID = first+second+"1";


        }



        return incrementID;
    }

}
