/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 5/30/2022
 * Time        : 11:53 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SignController {
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXPasswordField txtPasswordField;
    public ImageView vision;
    public ImageView visionOff;
    public JFXButton btnSignIn;

    public void initialize(){
        visionOff.setVisible(false);
        txtPassword.setVisible(false);
    }

    public void userNameOnKeyRelease(KeyEvent keyEvent) {
    }

    public void passwordOnKeyRelease(KeyEvent keyEvent) {
    }


    public void signInOnAction(ActionEvent actionEvent) {
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
