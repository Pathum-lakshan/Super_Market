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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ManageCustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageFormController {
    public JFXTextField txtCustomerName;
    public AnchorPane WorkingContex;
    public JFXTextField txtCustomerNIC;
    public JFXTextField txtCustomerPhoneNumber;
    public JFXButton btnAddCustomer;

    public TableView<CustomerDTO> tblCustomer;
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

    private final ManageCustomerBO manageCustomerBO = (ManageCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGECUSTOMER);

    public JFXTextField txtCustomerID;

    public void initialize(){
        colCusID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colCusPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        loadAllTable();

        txtCustomerID.setDisable(true);
        txtCustomerName.setDisable(true);
        txtCustomerAddress.setDisable(true);
        txtCustomerNIC.setDisable(true);
        txtCustomerPhoneNumber.setDisable(true);


        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            btnAddCustomer.setText(newValue != null ? "Update Customer" : "Add Customer");

            if (newValue != null) {
                txtCustomerID.setText(newValue.getId());
                txtCustomerName.setText(newValue.getName());
                txtCustomerAddress.setText(newValue.getAddress());
                txtCustomerNIC.setText(newValue.getNic());
                txtCustomerPhoneNumber.setText(newValue.getPhoneNumber());

                txtCustomerID.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
                txtCustomerNIC.setDisable(false);
                txtCustomerPhoneNumber.setDisable(false);
            }
        });

    }

    private void loadAllTable() {
        loadAllCustomer();
        loadAllOrder();
    }

    private void loadAllOrder() {

    }

    private void loadAllCustomer() {
        tblCustomer.getItems().clear();
        /*Get all customers*/
        try {

            //Loos Coupling
            ArrayList<CustomerDTO> allCustomers = manageCustomerBO.loadAllCustomers();


            for (CustomerDTO customer : allCustomers) {
                tblCustomer.getItems().add(customer);

            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

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



    public void addOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {


        txtCustomerID.clear();
        txtCustomerID.setText(manageCustomerBO.generateNewId());
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerNIC.clear();
        txtCustomerPhoneNumber.clear();
        btnAddCustomer.setText("Add Customer");

        txtCustomerID.setDisable(false);
        txtCustomerName.setDisable(false);
        txtCustomerAddress.setDisable(false);
        txtCustomerNIC.setDisable(false);
        txtCustomerPhoneNumber.setDisable(false);
    }
}
