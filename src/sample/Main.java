package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    Stage window;
    ComboBox<String> comboBox;
    ListView<String> listView;
    TableView<Product> table;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        GridPane grid = new GridPane();
        Scene scene1 = new Scene(grid, 550, 350);
        window.setScene(scene1);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5,5,5,5));
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5, 5, 10,5 ));
        hBox.setAlignment(Pos.CENTER_RIGHT);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(vBox);
        Scene scene2 = new Scene(borderPane, 550, 350);


        window.setTitle("Login");

        //Checkbox
        CheckBox box1 = new CheckBox("Bacon");
        box1.setSelected(true);
        CheckBox box2 = new CheckBox("Tuna");

        //Dropdown menü
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        //getItems returns the ObservableList objekt which yiz can add items to
        choiceBox.getItems().add(" ");
        choiceBox.getItems().addAll("Apples", "Bananas", "Stawberry");
        choiceBox.setValue("Apples");
        //Listen to changes
        choiceBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> System.out.println("Alter Wert: " + oldValue));

        //Dropdown menü 2
        comboBox = new ComboBox<>();
        comboBox.setEditable(true);
        comboBox.setPromptText("Gemüse");
        comboBox.getItems().addAll(
                "Gurke",
                "Paprika",
                "Tomate"
        );
        comboBox.setOnAction(e -> System.out.println("User selected: " + comboBox.getValue()));

        //Liste
        listView = new ListView<>();
        listView.getItems().addAll("The Big Short", "Moneyball", "Wall Street");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //Anordnung

        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(box1, 3,1);
        GridPane.setConstraints(box2, 3, 2);
        GridPane.setConstraints(choiceBox,1, 6);
        GridPane.setConstraints(comboBox, 1, 5);
        GridPane.setConstraints(listView, 1, 7);

        //Label Belag
        Label belagLabel = new Label("Topping:");
        GridPane.setConstraints(belagLabel, 2, 1);

        //Label Name
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 1);

        //Input Name
        TextField nameInput = new TextField();
        nameInput.setPromptText("Username");
        GridPane.setConstraints(nameInput, 1, 1);

        //Label Password
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 2);

        //Input Password
        TextField passInput = new TextField();
        passInput.setPromptText("Password");
        GridPane.setConstraints(passInput, 1, 2);

        //Label Vegetables
        Label vegetableLabel = new Label("Vegetables:");
        GridPane.setConstraints(vegetableLabel, 0, 5);

        //Label Movie
        Label movieLabel = new Label("Movie:");
        GridPane.setConstraints(movieLabel, 0, 7);

        //Scene2
        //Button Abmelden
        Button logoutButton = new Button("logout");
        logoutButton.setAlignment(Pos.BOTTOM_RIGHT);
        logoutButton.setOnAction(e -> window.setScene(scene1));

        //Name column
        TableColumn<Product, String> nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Product, Double> priceColumn = new TableColumn("Preis");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //quantity  column
        TableColumn<Product, Integer> quantityColumn = new TableColumn("Menge");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        hBox.getChildren().add(logoutButton);
        vBox.getChildren().add(table);




        //LoginButton + Ausgabe Konsole
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 9);
        loginButton.setOnAction(e -> {
                    printLoginData(nameInput, passInput);
                    handleOptions(box1, box2);
                    getChoiceBox(choiceBox);
                    getComboBox((comboBox));
                    getListView();
                    window.setScene(scene2);
                    window.setTitle("Table");
        });

        //Ausgabe der Werte über Enter Button wenn ChoiceBox is selected
        choiceBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ENTER)) {
                    printLoginData(nameInput, passInput);
                    handleOptions(box1, box2);
                    getComboBox(comboBox);
                    getChoiceBox(choiceBox);
                    getListView();
                }
            }
        });

        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, box1, box2, loginButton, choiceBox, comboBox, vegetableLabel, belagLabel, listView, movieLabel);

        window.setOnCloseRequest(e -> closeProgramm());

        window.show();
    }

    //Get all of the products
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop", 859.00, 20));
        products.add(new Product("monitor", 269.00, 20));
        products.add(new Product("desk", 329.00, 20));
        products.add(new Product("chair", 499.00, 20));
        products.add(new Product("keyboard", 89.00, 20));
        products.add(new Product("trackpad", 49.00, 20));
        return products;
    }

    private void closeProgramm(){
        Boolean answer = ConfirmBox.display("Title", "Sure you want to exit?");
        if(answer){
            window.close();
        }

    }

    private boolean printLoginData(TextField nameInput, TextField passInput){
        try{
            int userName = Integer.parseInt(nameInput.getText());
            System.out.println("Error: " + userName + " ist kein Name");
            return false;
        }catch (NumberFormatException e){
            System.out.println("Username: " + nameInput.getText());
        }
        System.out.println("Passwort: " + passInput.getText());
        return true;
    }

    private void handleOptions(CheckBox box1, CheckBox box2){
        String message = "Users order:\n";

        if(box1.isSelected())
            message += "Bacon\n";

        if(box2.isSelected())
            message += "Tuna\n";

        System.out.println(message);
    }

    private void getChoiceBox(ChoiceBox<String> choiceBox){
        String food = choiceBox.getValue();
        System.out.println(food);
    }

    private void getComboBox(ComboBox<String> comboBox){
        System.out.println(comboBox.getValue());
    }

    private void getListView(){
        String message = "";
        ObservableList<String> movie;
        movie = listView.getSelectionModel().getSelectedItems();
        for (String m: movie) {
            message += m + "\n";
        }
        System.out.println(message);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
