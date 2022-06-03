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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.view.util.Util;
import lk.ijse.pos.view.tdm.OrdersTM;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    public JFXButton btnAdd;

    private String orderId;
    private String cusID;




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

      tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {

          if (selectedOrderDetail != null) {
              cmbItemCode.setDisable(true);
              cmbItemCode.setValue(selectedOrderDetail.getItemCode());
              btnAdd.setText("Update");
              txtQtyOnHand.setText(txtQtyOnHand.getText() + selectedOrderDetail.getQtyOnHand() + "");
              txtQtyOnHand.setText(selectedOrderDetail.getQtyOnHand() + "");
          } else {
              btnAdd.setText("Add");
              cmbItemCode.setDisable(false);
              cmbItemCode.getSelectionModel().clearSelection();
              txtQtyOnHand.clear();
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

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

      OrdersTM selectItem = tblOrder.getSelectionModel().getSelectedItem();

      tblOrder.getItems().removeAll(selectItem);

        ItemDTO itemDTO = placeOrderBO.searchItem(selectItem.getItemCode());

        int qty = itemDTO.getQtyOnHand();

        int now = Integer.parseInt(selectItem.getQtyOnHand());

        int updatedQty= now + qty;

        placeOrderBO.UpdateItemQty(new ItemDTO(selectItem.getItemCode(),updatedQty));

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

              if (txtQtyOnHand.getText().equals("")){
                  Util.PontificateError("empty qtyOnHand","ERROR");
              }else {
                  int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

                  double unitPrice = Double.parseDouble(txtItemUnitPrice.getText());

                  int itemQty = Integer.parseInt(txtItemQty.getText());



                  if (itemQty>qtyOnHand){

                      cusID= String.valueOf(cmbCustomerID.getValue());

                      cmbCustomerID.setDisable(true);

                      String totalOfQtyOnHand= String.valueOf(qtyOnHand*unitPrice);

                      String itemCode= String.valueOf(cmbItemCode.getValue());
                      String qty = txtQtyOnHand.getText();
                      BigDecimal unitsPrice = new BigDecimal(txtItemUnitPrice.getText()).setScale(2);
                      String total = String.valueOf(unitsPrice.multiply(new BigDecimal(qty)).setScale(2));
                      boolean exists = tblOrder.getItems().stream().anyMatch(detail -> detail.getItemCode().equals(itemCode));



                      if (exists) {
                          OrdersTM orderDetailTM = tblOrder.getItems().stream().filter(detail -> detail.getItemCode().equals(itemCode)).findFirst().get();

                          if (btnAdd.getText().equalsIgnoreCase("Update")) {
                              orderDetailTM.setQtyOnHand(qty);
                              orderDetailTM.setTotal(total);
                              tblOrder.getSelectionModel().clearSelection();
                          } else {
                              int x= Integer.parseInt(orderDetailTM.getQtyOnHand());
                              int y = Integer.parseInt(qty);

                              String a = String.valueOf(x+y);

                              orderDetailTM.setQtyOnHand(a);
                            String  totals = String.valueOf(new BigDecimal(orderDetailTM.getQtyOnHand()).multiply(unitsPrice).setScale(2));
                              orderDetailTM.setTotal(totals);
                          }
                          tblOrder.refresh();
                      } else {
                          tblOrder.getItems().add(new OrdersTM(txtCustomerName.getText(),txtItemName.getText(),txtItemUnitPrice.getText(),txtQtyOnHand.getText(),totalOfQtyOnHand,(String) cmbItemCode.getValue()));
                      }

                      int qty1 = itemQty-qtyOnHand;

                      placeOrderBO.UpdateItemQty(new ItemDTO((String) cmbItemCode.getValue(),qty1));


                     // tblOrder.setItems(obTmList);
                      txtItemUnitPrice.clear();
                      txtItemQty.clear();
                      txtItemName.clear();
                     // txtCustomerPhone.clear();
                      TxtItemDescription.clear();
                     // txtCustomerAddress.clear();
                      cmbItemCode.setValue(null);
                     // cmbCustomerID.setValue(null);
                      //txtCustomerName.clear();
                      txtQtyOnHand.clear();


                  }else {
                      Util.PontificateError("Invalid qty","ERROR");
                  }

              }




          }else {Util.PontificateError("Select Item","ERROR");}

      }else {
          Util.PontificateError("Select Customer","ERROR");
      }





      calculateTotal();

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

//import javafx.beans.binding.Bindings;
        if(Bindings.isEmpty(tblOrder.getItems()).get()) {
            //EMPTY
            Util.PontificateError("cant found order","ERROR");
            cmbCustomerID.setDisable(false);

        }else {








            boolean b =  saveOrder(orderId,LocalDate.now(),cusID,Double.parseDouble(String.valueOf(lblTotal.getText())), tblOrder.getItems().stream().map(tm -> new OrderDetailDTO(orderId,
                    tm.getItemCode() ,BigDecimal.valueOf(Double.parseDouble(tm.getUnitPrice())),Integer.parseInt(tm.getQtyOnHand()) )).collect(Collectors.toList()));







            if (b) {

                Util.notifications("Order has been placed successfully","SUCCESSFULLY");
            } else {

                Util.PontificateError("Order has not been placed successfully","NOT SUCCESSFULLY");
            }

            orderId = placeOrderBO.generateNewOrderId();
            lblOrderID.setText(orderId);
            cmbCustomerID.getSelectionModel().clearSelection();
            cmbItemCode.getSelectionModel().clearSelection();
            tblOrder.getItems().clear();
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
            cmbCustomerID.setDisable(false);

            calculateTotal();
        }





    }
    private void calculateTotal() {

         BigDecimal total = new BigDecimal(0);
        for (OrdersTM detail : tblOrder.getItems()) {

            double x = Double.parseDouble(detail.getTotal());

            BigDecimal y = BigDecimal.valueOf(x);

            total = total.add(y);
        }
        lblTotal.setText(String.valueOf(total));
    }
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId,double total, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
      return   placeOrderBO.placeOrder(
              new OrderDTO(orderId,orderDate,customerId,total,orderDetails));

    }

}
