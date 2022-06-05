package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails, String> {

    boolean updateDetail(OrderDetails entity,String itemCode) throws SQLException, ClassNotFoundException;
    boolean deleteItem(OrderDetails orderDetails) throws SQLException, ClassNotFoundException;

}
