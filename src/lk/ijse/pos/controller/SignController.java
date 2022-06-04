/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 5/30/2022
 * Time        : 11:53 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignController {
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXPasswordField txtPasswordField;
    public ImageView vision;
    public ImageView visionOff;
    public JFXButton btnSignIn;

    public AnchorPane signInContext;
    public JFXComboBox cmbUserType;

    public void initialize(){
        visionOff.setVisible(false);
        txtPassword.setVisible(false);
        cmbUserType.getItems().add("Admin");
        cmbUserType.getItems().add("Cashier");
    }

    public void userNameOnKeyRelease(KeyEvent keyEvent) {
    }

    public void passwordOnKeyRelease(KeyEvent keyEvent) {
    }


    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        if (cmbUserType.getValue()!=null){
            if (cmbUserType.getValue().equals("Cashier")){
                Stage stage=(Stage) signInContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardCashierForm.fxml"))));
                stage.centerOnScreen();
            }else {
                Stage stage=(Stage) signInContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminForm.fxml"))));
                stage.centerOnScreen();
            }
        }


    }



    public void enterMouseOnAction(MouseEvent mouseEvent) {
        visionOff.setVisible(true);
        txtPasswordField.setVisible(false);
        txtPassword.setVisible(true);
        txtPassword.setText(txtPasswordField.getText());
    }

    public void exitMouseOnAction(MouseEvent mouseEvent) {

        visionOff.setVisible(false);
        txtPasswordField.setVisible(true);
        txtPassword.setVisible(false);
    }
}
