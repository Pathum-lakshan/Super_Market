/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/27/2022
 * Time        : 4:58 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.oid=OrderDetails.oid where Orders.oid=?;", id);
        ArrayList<CustomDTO> orderRecords = new ArrayList();
        while (rst.next()) {
            orderRecords.add(new CustomDTO(rst.getString(1), LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6)));
        }
        return orderRecords;
    }

    public boolean updateItem (String code, int qty) throws SQLException, ClassNotFoundException {

     return    SQLUtil.execute("UPDATE item set Qty=? where ItemCode=?",qty,code);
    }


}
