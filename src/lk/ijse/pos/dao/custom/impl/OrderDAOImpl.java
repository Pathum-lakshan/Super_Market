package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class OrderDAOImpl implements OrderDao {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("insert into orders values (?,?,?,?)", entity.getOid(), entity.getDate(), entity.getCusID(),entity.getTotal());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Orders SET total=? WHERE OrderId=?", entity.getTotal(), entity.getOid());

    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Orders WHERE orderId=?", s);
        if (rst.next()) {
            return new Order(rst.getString(1), LocalDate.parse((CharSequence) rst.getDate(2)), rst.getString(3), rst.getDouble(4));
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT orderId FROM `Orders` WHERE orderId=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Orders WHERE OrderId=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       /* ResultSet rst = SQLUtil.executeQuery("SELECT OrderId FROM `Orders` ORDER BY OrderId DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("OrderId").replace("OID-", "")) + 1)) : "OID-001";
  */


        String table ="Orders";
        String incrementID=null;

        ResultSet maxId = SQLUtil.execute("SELECT CONCAT(MAX(0+SUBSTRING(OrderId,3))) FROM `Orders`");

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
