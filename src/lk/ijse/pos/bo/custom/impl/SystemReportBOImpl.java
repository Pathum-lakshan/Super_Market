/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 9:25 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SystemReportBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class SystemReportBOImpl implements SystemReportBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDao orderDAO = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    public ArrayList<OrderDTO> loadAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll();

        ArrayList<OrderDTO> allOrder = new ArrayList<>();

        for (Order order : all){
            allOrder.add(new OrderDTO(order.getOid(),order.getDate(),order.getCusID(),order.getTotal()));
        }
        return allOrder;
    }

}
