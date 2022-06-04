/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/27/2022
 * Time        : 10:25 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.dao.custom.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private final DAOFactory daoFactory =  DAOFactory.getDaoFactory();

    private final CustomerDAO customerDAO = (CustomerDAO) daoFactory.getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) daoFactory.getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDao orderDAO = (OrderDao) daoFactory.getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) daoFactory.getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) daoFactory.getDAO(DAOFactory.DAOTypes.QUERYDAO);







    public boolean placeOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {


        /*Transaction*/

        Connection connection = DBConnection.getDbConnection().getConnection();

        if (orderDAO.exist(orderDTO.getOrderId())) {

        }

        connection.setAutoCommit(false);
        boolean save = orderDAO.save(new Order(orderDTO.getOrderId(), orderDTO.getOrderDate(),orderDTO.getCusID(),orderDTO.getTotal()));

        if (!save) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detail : orderDTO.getOrderDetails()) {
            boolean save1 = orderDetailsDAO.save(new OrderDetails(detail.getOid(),detail.getItemCode(),detail.getQty(),detail.getUnitPrice()));
            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            ItemDTO item = searchItem(detail.getItemCode());

            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            //update item

            boolean update = itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice()));

            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;


    }

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getId(),search.getName(),search.getAddress(),search.getNic(),search.getPhone());
    }

    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(code);
        return new ItemDTO(search.getCode(),search.getName(),search.getDescription(),search.getUnitPrice(),search.getQtyOnHand());
    }

    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }


    public String generateNewOrderId() throws SQLException, ClassNotFoundException {

            return orderDAO.generateNewID();

    }
    public ArrayList<CustomerDTO> loadAllCustomerIds() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> all = customerDAO.getAll();

        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        for (Customer customer:all
        ) {
            allCustomer.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }

        return allCustomer;

    }

    public   ArrayList<ItemDTO>  loadAllItemCodes() throws SQLException, ClassNotFoundException {

        ArrayList<Item> all = itemDAO.getAll();

        ArrayList<ItemDTO> allItem = new ArrayList<>();

        for (Item item:all
             ) {
            allItem.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand()));
        }

        return allItem;
    }

    @Override
    public boolean UpdateItemQty(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
     return queryDAO.updateItem(itemDTO.getCode(),itemDTO.getQtyOnHand());

    }

}

