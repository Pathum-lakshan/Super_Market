/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/29/2022
 * Time        : 6:04 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.entity;

import java.time.LocalDate;

public class Order {
    private String oid;
    private LocalDate date;
    private String customerID;
    private String ItemCode;
    private String qtyOnHand;

    public Order() {
    }

    public Order(String oid, LocalDate date, String customerID, String itemCode, String qtyOnHand) {
        this.oid = oid;
        this.date = date;
        this.customerID = customerID;
        ItemCode = itemCode;
        this.qtyOnHand = qtyOnHand;
    }

    public Order(String oid, LocalDate date, String customerID) {
        this.oid = oid;
        this.date = date;
        this.customerID = customerID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
