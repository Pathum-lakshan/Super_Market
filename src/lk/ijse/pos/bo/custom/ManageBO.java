/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/28/2022
 * Time        : 12:57 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageBO extends SuperBO {
    ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean UpdateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNewId () throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> loadAllOrderDetails() throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> loadAllOrder() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> loadAllItem()throws SQLException, ClassNotFoundException;

    boolean deleteOrderDetail(String id)throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id)throws SQLException, ClassNotFoundException;
    boolean UpdateOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean UpdateOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
}
