/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/2/2022
 * Time        : 1:45 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.view.tdm;

public class OrdersTM {
    private String customerName;
    private String itemName;
    private String unitPrice;
    private String qtyOnHand;
    private String Total;
    private String itemCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public OrdersTM(String customerName, String itemName, String unitPrice, String qtyOnHand, String total, String itemCode) {
        this.customerName = customerName;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        Total = total;
        this.itemCode = itemCode;
    }

    public OrdersTM(String customerName, String itemName, String unitPrice, String qtyOnHand, String total) {
        this.customerName = customerName;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        Total = total;
    }

    public OrdersTM() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "OrdersTM{" +
                "customerName='" + customerName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", Total='" + Total + '\'' +
                '}';
    }
}
