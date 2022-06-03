/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/28/2022
 * Time        : 12:31 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ManageCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerBOImpl implements ManageCustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrderDao orderDAO = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> all = customerDAO.getAll();

        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        for (Customer customer: all
             ) {
            allCustomer.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(),customer.getNic(),customer.getPhone()));
        }

        return allCustomer;
    }
    public ArrayList<OrderDetailDTO> loadAllOrderDetails() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();

        ArrayList<OrderDetailDTO> allOrders = new ArrayList<>();

        for (OrderDetails order: all
             ) {
            allOrders.add(new OrderDetailDTO(order.getOid(),order.getItemCode(),order.getUnitPrice(),order.getQty()));
        }

        return allOrders;
    }

    public ArrayList<OrderDTO> loadAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll();

        ArrayList<OrderDTO> allOrder = new ArrayList<>();

        for (Order order : all){
            allOrder.add(new OrderDTO(order.getOid(),order.getDate(),order.getCusID(),order.getTotal()));
        }
        return allOrder;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItem = new ArrayList<>();

        for (Item item:all
             ) {
            allItem.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getName()));
        }
        return allItem;
    }

    @Override
    public boolean deleteOrderDetail(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.delete(id);
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
       return customerDAO.save(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getNic(), customerDTO.getPhoneNumber()));
    }

    public boolean UpdateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
      return   customerDAO.update(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getNic(), customerDTO.getPhoneNumber()));
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.delete(id);
    }

    public String generateNewId () throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

}
