package Controller.DashBoard;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public AnchorPane lodeFormController;

    public void btnItemFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/view_item_form.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/viewcustomer_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void lodeFormContent(MouseEvent mouseEvent) {


    }

    public void btnPlaceOrderFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/view_order_form_controller.fxml");

        assert resource!=null;
        Parent load = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(load);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

        Label message = new Label("Bye Bye");
        message.setStyle("-fx-font-size: 24px; -fx-text-fill: #ff0000;");

        StackPane root = new StackPane(message);
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), root);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(e -> {
            stage.close();
            ((Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
        fadeTransition.play();
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/dash-board-clone.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL resource = this.getClass().getResource("/view/dash-board-clone.fxml");

        assert resource!=null;
        Parent lode = null;
        try {
            lode = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnSettingFormOnAction(ActionEvent actionEvent) {
    }
}
