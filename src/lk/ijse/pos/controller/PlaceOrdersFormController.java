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
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.bo.custom.impl.PlaceOrderBOImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.view.tdm.OrderDetailTM;
import lk.ijse.pos.view.tdm.OrdersTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaceOrdersFormController {
    public AnchorPane WorkingContex;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerPhone;
    public JFXComboBox cmbCustomerID;
    public TableView <OrdersTM>tblOrder;
    public TableColumn colCustomerName;
    public TableColumn colItemName;
    public TableColumn colQtyOnHand;
    public TableColumn colTotal;
    public JFXTextField txtItemName;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQty;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtCustomerAddress;
    public JFXTextField TxtItemDescription;
    public JFXTextField txtQtyOnHand;
    public Label lblTotal;

    public Label lblOrderID;
    public TableColumn colItemUnitPrice;

    private String orderId;


    private final PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

    ObservableList<OrdersTM> obTmList = FXCollections.observableArrayList();

  public void initialize() throws SQLException, ClassNotFoundException {

      colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
      colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
      colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
      colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
      colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));


      orderId = generateNewOrderId();
      lblOrderID.setText(orderId);

      cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          if (newValue != null) {
              try {
                  CustomerDTO search = placeOrderBO.searchCustomer(newValue + "");
                  txtCustomerName.setText(search.getName());
                  txtCustomerAddress.setText(search.getAddress());
                  txtCustomerPhone.setText(search.getPhoneNumber());
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }
          }
      });
      cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          if (newValue != null) {
              try {
                  ItemDTO item = placeOrderBO.searchItem(newValue + "");

                  txtItemName.setText(item.getItemName());
                  txtItemQty.setText(String.valueOf(item.getQtyOnHand()));
                  txtItemUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                  TxtItemDescription.setText(item.getDescription());
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }
          }
      });


      loadAllCustomerIds();
      loadAllItemCodes();
  }

    public void txtSupplyItemNameOnKeyRelease(KeyEvent keyEvent) {

    }

    public void txtSupplyCostOnAction(KeyEvent keyEvent) {
    }

    public void txtSupplyItemQtyOnAction(KeyEvent keyEvent) {
    }

    public void supplyIDOnKeyRelese(KeyEvent keyEvent) {
    }
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {

        return placeOrderBO.generateNewOrderId();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

      OrdersTM selectItem = tblOrder.getSelectionModel().getSelectedItem();

      tblOrder.getItems().removeAll(selectItem);

      String code = placeOrderBO.searchItem(selectItem.)




    }

    private void loadAllCustomerIds() throws SQLException, ClassNotFoundException {


        ArrayList<CustomerDTO> all =placeOrderBO.loadAllCustomerIds();
        for (CustomerDTO customerDTO : all) {
            cmbCustomerID.getItems().add(customerDTO.getId());
        }

    }
    private void loadAllItemCodes() {
        try {

            ArrayList<ItemDTO> all = placeOrderBO.loadAllItemCodes();
            for (ItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getCode());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {



      if (cmbCustomerID.getValue()!= null ){
          if (cmbItemCode.getValue() != null){

              if (txtQtyOnHand.getText().equals("")){new Alert(Alert.AlertType.ERROR, "empty qtyOnHand").show();}else {
                  int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

                  double unitPrice = Double.parseDouble(txtItemUnitPrice.getText());

                  int itemQty = Integer.parseInt(txtItemQty.getText());



                  if (itemQty>qtyOnHand){

                      String totalOfQtyOnHand= String.valueOf(qtyOnHand*unitPrice);

                      obTmList.add(new OrdersTM(txtCustomerName.getText(),txtItemName.getText(),txtItemUnitPrice.getText(),txtQtyOnHand.getText(),totalOfQtyOnHand));







                      int qty = itemQty-qtyOnHand;

                      placeOrderBO.UpdateItemQty(new ItemDTO((String) cmbItemCode.getValue(),qty));


                      tblOrder.setItems(obTmList);
                      txtItemUnitPrice.clear();
                      txtItemQty.clear();
                      txtItemName.clear();
                      txtCustomerPhone.clear();
                      TxtItemDescription.clear();
                      txtCustomerAddress.clear();
                      cmbItemCode.setValue(null);
                      cmbCustomerID.setValue(null);
                      txtCustomerName.clear();
                      txtQtyOnHand.clear();


                  }else {new Alert(Alert.AlertType.ERROR, "Invalid qty").show();}

              }




          }else {new Alert(Alert.AlertType.ERROR, "Select Item").show();}
      }else {new Alert(Alert.AlertType.ERROR, "Select Customer").show();}






    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }
}