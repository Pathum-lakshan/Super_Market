/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/29/2022
 * Time        : 6:04 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private String oid;
    private LocalDate date;
    private String cusID;
    private double total;

    public Order(String oid, LocalDate date, String cusName, double total) {
        this.oid = oid;
        this.date = date;
        this.cusID = cusName;
        this.total = total;
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
        return "Order{" +
                "oid='" + oid + '\'' +
                ", date=" + date +
                ", cusName='" + cusID + '\'' +
                ", total=" + total +
                '}';
    }
}
