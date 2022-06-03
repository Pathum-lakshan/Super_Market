package lk.ijse.pos.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class OrderDetailDTO implements Serializable {

    private String oid;
    private String itemCode;

    private BigDecimal unitPrice;
    private int qty;
    private double total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String itemCode, int qty, BigDecimal unitPrice) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetailDTO(String oid, String itemCode, BigDecimal unitPrice, int qty, double total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }

    public OrderDetailDTO(String oid, String itemCode, BigDecimal unitPrice, int qty) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetailDTO{");
        sb.append("oid='").append(oid).append('\'');
        sb.append(", itemCode='").append(itemCode).append('\'');
        sb.append(", qty=").append(qty);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append('}');
        return sb.toString();
    }
}
