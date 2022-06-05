package lk.ijse.pos.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class OrderDetailDTO implements Serializable {

    private String oid;
    private String itemCode;
    private String itemName;

    private BigDecimal unitPrice;
    private int qty;
    private double total;

    public OrderDetailDTO(String oid, String itemCode, int qty, BigDecimal unitPrice, double total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }

    public OrderDetailDTO(String oid, String itemCode) {
        this.oid = oid;
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oid, String itemCode, String itemName, BigDecimal unitPrice, int qty, double total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
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
        return "OrderDetailDTO{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
