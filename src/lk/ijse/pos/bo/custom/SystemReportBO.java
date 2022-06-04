/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 9:25 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SystemReportBO extends SuperBO {
    ArrayList<OrderDTO> loadAllOrder() throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO>  loadAllItemCodes() throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> loadAllOrderDetails() throws SQLException, ClassNotFoundException;
}
