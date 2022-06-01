/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 5/31/2022
 * Time        : 9:18 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ManageFormController {
    public JFXTextField txtCustomerName;
    public AnchorPane WorkingContex;
    public JFXTextField txtCustomerNIC;
    public JFXTextField txtCustomerPhoneNumber;
    public JFXButton btnAddCustomer;
    public JFXComboBox cmbCustomerID;
    public TableView tblCustomer;
    public TableColumn colCusID;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCusNIC;
    public TableColumn colCusPhoneNumber;
    public JFXTextField txtCustomerAddress;
    public TableView tblOrder;
    public TableColumn colOrderID;
    public TableColumn colOrderDate;
    public TableColumn colOrderQtyOnHand;
    public TableColumn colOrderCusID;
    public TableColumn colOrderItemCode;
    public JFXTextField txtOrderDate;
    public JFXButton btnModifyOrder;
    public JFXComboBox cmbOrderID;
    public JFXTextField txtQtyOnHand;
    public JFXComboBox cmbItemCode;
    public JFXComboBox cmbOrderCustomerID;

    public void txtSupplyItemNameOnKeyRelease(KeyEvent keyEvent) {
    }

    public void txtSupplyItemQtyOnAction(KeyEvent keyEvent) {
    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
    }

    public void txtSupplyCostOnAction(KeyEvent keyEvent) {
    }

    public void supplyIDOnKeyRelese(KeyEvent keyEvent) {
    }

    public void modifyOrderOnAction(ActionEvent actionEvent) {
    }

    public void cmbSupplyIDOnAction(ActionEvent actionEvent) {
    }
}
