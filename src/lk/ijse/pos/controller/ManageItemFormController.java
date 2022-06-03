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
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ManageItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.view.tdm.ItemTM;

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
    private final ManageItemBO manageItemsBO =(ManageItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGEITEM);
    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadAllItem();
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
    }

    public void txtItemUnitPriceOnKeyRelease(KeyEvent keyEvent) {
    }

    public void txtItemQtyOnKeyRelease(KeyEvent keyEvent) {
    }

    public void AddItemOnAction(ActionEvent actionEvent) {
    }

    public void txtItemDescriptionOnKeyRelease(KeyEvent keyEvent) {
    }

    public void addOnAction(MouseEvent mouseEvent) {
    }
}
