package lk.ijse.pos.dto;

import java.io.Serializable;
import java.math.BigDecimal;



public class ItemDTO implements Serializable {
    private String code;

    private String ItemName;
    private String description;
    private BigDecimal unitPrice;
    private int qtyOnHand;

    public ItemDTO(String code, String itemName, String description, BigDecimal unitPrice, int qtyOnHand) {
        this.code = code;
        ItemName = itemName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public ItemDTO(String code, int qty) {
        this.code = code;
        this.qtyOnHand = qty;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public ItemDTO() {
    }

    public ItemDTO(String code, String description, BigDecimal unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", ItemName='" + ItemName + '\'' +
                '}';
    }
}
