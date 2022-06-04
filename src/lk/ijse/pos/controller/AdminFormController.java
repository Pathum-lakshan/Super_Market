/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 12:28 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormController {
    public AnchorPane DashBoardContext;
    public Label lblTimeDate;
    public AnchorPane WorkingContext;
    public void initialize() throws IOException {
        WorkingContext.getChildren().clear();
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/ManageItemForm.fxml"));
        WorkingContext.getChildren().add(parent);
    }

    public void manageItemOnAction(ActionEvent actionEvent) throws IOException {
        WorkingContext.getChildren().clear();
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/ManageItemForm.fxml"));
        WorkingContext.getChildren().add(parent);
    }

    public void SystemReportOnAction(ActionEvent actionEvent) throws IOException {
        WorkingContext.getChildren().clear();
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/SystemReportForm.fxml"));
        WorkingContext.getChildren().add(parent);
    }

    public void closeMouseOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) DashBoardContext.getScene().getWindow();
        stage.close();
    }

    public void homeOnMouseClick(MouseEvent mouseEvent) throws IOException {
        WorkingContext.getChildren().clear();
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/ManageItemForm.fxml"));
        WorkingContext.getChildren().add(parent);
    }

    public void logOutMouseClickOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stag=(Stage) DashBoardContext.getScene().getWindow();
        stag.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Sign.fxml"))));
        stag.centerOnScreen();
    }
}
