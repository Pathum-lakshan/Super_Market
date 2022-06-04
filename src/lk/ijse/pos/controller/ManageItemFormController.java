/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 12:42 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ManageItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.view.tdm.ItemTM;
import lk.ijse.pos.view.util.Util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageItemFormController {
    public AnchorPane WorkingContex;
    public JFXTextField txtItemName;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQty;
    public JFXButton btnAddItem;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemCode;
    public TableView <ItemTM>tblItem;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQty;
    private  ItemTM newValue1;
    private final ManageItemBO manageItemsBO =(ManageItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGEITEM);
    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadAllItem();

        disables();
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             newValue1 = newValue;
            btnAddItem.setDisable(newValue == null);
            btnAddItem.setText(newValue != null ? "Delete Item" : "Add Item");

            if (newValue != null) {
               txtItemCode.setText(newValue.getCode());
               txtItemName.setText(newValue.getName());

               String s = String.valueOf(newValue.getUnitPrice());

               int x = s.indexOf('.');

               String y = s.substring(0,x);

               txtItemDescription.setText(newValue.getDescription());
               txtItemUnitPrice.setText(y);
               txtItemQty.setText(String.valueOf(newValue.getQtyOnHand()));

                txtItemCode.setDisable(false);
                txtItemName.setDisable(false);
                txtItemDescription.setDisable(false);
                txtItemUnitPrice.setDisable(false);
                txtItemQty.setDisable(false);
                btnAddItem.setDisable(false);
            }
        });
btnAddItem.setDisable(true);

        checkValidate(txtItemName,"^[A-z ]+$",btnAddItem);
        checkValidate(txtItemDescription,"^[A-z ]+$",btnAddItem);
        checkValidate(txtItemQty,"^[0-9]{0,3}$",btnAddItem);
        checkValidate(txtItemUnitPrice,"^[0-9]+$",btnAddItem);


    }
    private void checkValidate(JFXTextField jfxTextField,String regex,JFXButton jfxButton) {
        jfxTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches(regex)){
                jfxButton.setDisable(false);
                jfxTextField.setFocusColor(Color.GREEN);
            }
            else {
                jfxButton.setDisable(true);
                jfxTextField.setFocusColor(Color.RED);
            }


        });
    }
    private void disables() {
        txtItemCode.setDisable(true);
        txtItemName.setDisable(true);
        txtItemDescription.setDisable(true);
        txtItemUnitPrice.setDisable(true);
        txtItemQty.setDisable(true);
    }

    private void loadAllItem() {

        tblItem.getItems().clear();
        try {
            /*Get all items*/
            //Loos Coupling
            ArrayList<ItemDTO> allItems = manageItemsBO.loadAllItems();

            for (ItemDTO item : allItems) {
                tblItem.getItems().add(new ItemTM(item.getCode(),item.getItemName(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtItemNameOnKeyRelease(KeyEvent keyEvent) {
        if (newValue1!=null){checkText(txtItemName,newValue1.getName());}

    }

    public void txtItemUnitPriceOnKeyRelease(KeyEvent keyEvent) {
        if (newValue1!=null){checkText(txtItemUnitPrice, String.valueOf(newValue1.getUnitPrice()));}

    }

    public void txtItemQtyOnKeyRelease(KeyEvent keyEvent) {
        if (newValue1!=null){checkText(txtItemQty, String.valueOf(newValue1.getQtyOnHand()));}

    }

    public void AddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (btnAddItem.getText().equals("Add Item")){

        boolean b=    manageItemsBO.saveItems(new ItemDTO(txtItemCode.getText(),txtItemName.getText(),
                    txtItemDescription.getText(), BigDecimal.valueOf(Long.parseLong(txtItemUnitPrice.getText())),Integer.parseInt(txtItemQty.getText())));

        if (b){
            Util.notifications("Item has been saved successfully","SUCCESSFULLY");
            txtItemCode.clear();
            txtItemName.clear();
            txtItemDescription.clear();
            txtItemUnitPrice.clear();
            txtItemQty.clear();
            newValue1=null;
            btnAddItem.setDisable(true);
            loadAllItem();
            disables();
        }

        }
        else if (btnAddItem.getText().equals("Delete Item")){
            boolean b=   manageItemsBO.deleteItems(txtItemCode.getText());
            if (b){
                Util.notifications("Item has been Delete successfully","SUCCESSFULLY");
                loadAllItem();
                txtItemCode.clear();
                txtItemName.clear();
                txtItemDescription.clear();
                txtItemUnitPrice.clear();
                txtItemQty.clear();
                newValue1=null;
                btnAddItem.setDisable(true);
                disables();
            }
        }
        else {



            boolean b=    manageItemsBO.updateItems(new ItemDTO(txtItemCode.getText(),txtItemName.getText(),
                    txtItemDescription.getText(), BigDecimal.valueOf(Long.parseLong(txtItemUnitPrice.getText())),Integer.parseInt(txtItemQty.getText())));

            if (b){
                Util.notifications("Item has been Updated successfully","SUCCESSFULLY");
                loadAllItem();
                txtItemCode.clear();
                txtItemName.clear();
                txtItemDescription.clear();
                txtItemUnitPrice.clear();
                txtItemQty.clear();
                newValue1=null;
                btnAddItem.setDisable(true);
                disables();
            }


        }
    }

    public void txtItemDescriptionOnKeyRelease(KeyEvent keyEvent) {
        if (newValue1!=null){checkText(txtItemDescription,newValue1.getDescription());}


    }
    private void checkText(JFXTextField jfxTextField,String value) {
        if (newValue1==null){

            btnAddItem.setText("Add Item");
        }else if (jfxTextField.getText().equals(value)){

            btnAddItem.setText("Delete Item");
        }else {

            btnAddItem.setText("Update Item");
        }
    }

    public void addOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        txtItemCode.clear();
        txtItemName.clear();
        txtItemDescription.clear();
        txtItemUnitPrice.clear();
        txtItemQty.clear();
        newValue1=null;

        txtItemName.setDisable(false);
        txtItemDescription.setDisable(false);
        txtItemUnitPrice.setDisable(false);
        txtItemQty.setDisable(false);
        txtItemCode.setText(manageItemsBO.generateNewId());
        btnAddItem.setText("Add Item");
    }
}
