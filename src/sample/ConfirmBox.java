package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

     static boolean answer;



    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //create two buttons
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        HBox selection = new HBox(10);
        selection.setAlignment(Pos.CENTER);
        layout.getChildren().add(label);
        selection.getChildren().addAll(yesButton, noButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(layout);
        borderPane.setCenter(selection);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
