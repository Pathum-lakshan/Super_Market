package lk.ijse.pos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class OrderDTO  {
    private String orderId;
    private LocalDate orderDate;
    private String cusID;
    private double total;


    List<OrderDetailDTO> orderDetails;

    public OrderDTO(String orderId, double total) {
        this.orderId = orderId;
        this.total = total;
    }

    public OrderDTO(String orderId, LocalDate orderDate, String cusID, double total) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusID = cusID;
        this.total = total;
    }

    public OrderDTO() {
    }


    public OrderDTO(String orderId, LocalDate orderDate, String cusID, double total, List<OrderDetailDTO> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusID = cusID;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", cusID='" + cusID + '\'' +
                ", total=" + total +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

