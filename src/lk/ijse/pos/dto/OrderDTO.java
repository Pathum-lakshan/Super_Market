package lk.ijse.pos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class OrderDTO  {
    private String orderId;
    private LocalDate orderDate;
    private String cusName;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, LocalDate orderDate, String cusName, double total) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusName = cusName;
        this.total = total;
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

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
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
                ", cusName='" + cusName + '\'' +
                ", total=" + total +
                '}';
    }
}
