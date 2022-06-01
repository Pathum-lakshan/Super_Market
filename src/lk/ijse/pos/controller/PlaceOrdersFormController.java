/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 5/31/2022
 * Time        : 11:06 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class PlaceOrdersFormController {
    public AnchorPane WorkingContex;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerPhone;
    public JFXButton btnAddOnAction;
    public JFXComboBox cmbCustomerID;
    public TableView tblOrder;
    public TableColumn colCustomerName;
    public TableColumn colItemDescription;
    public TableColumn colItemName;
    public TableColumn colQtyOnHand;
    public TableColumn colTotal;
    public TableColumn colDelete;
    public JFXTextField txtItemName;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQty;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtCustomerAddress;
    public JFXTextField TxtItemDescription;
    public JFXTextField txtQtyOnHand;
    public Label lblTotal;
    public JFXButton btnPlaceOrderOnAction;
    public Label lblOrderID;




    public void txtSupplyItemNameOnKeyRelease(KeyEvent keyEvent) {
    }

    public void txtSupplyCostOnAction(KeyEvent keyEvent) {
    }

    public void txtSupplyItemQtyOnAction(KeyEvent keyEvent) {
    }

    public void supplyIDOnKeyRelese(KeyEvent keyEvent) {
    }
}
