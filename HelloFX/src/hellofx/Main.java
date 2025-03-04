/** Important terminology
 *  Stage: The window you are using in the application (like the main app window).
    Scene: What's displayed inside the window (the content of the stage).
    Node: Any element in the UI (like a button, label, or text field).
    Pane: A layout container that organizes nodes (e.g., VBox for vertical alignment, HBox for horizontal alignment).
    VBox: A layout that stacks nodes vertically.
    HBox: A layout that arranges nodes horizontally.
    StackPane: A layout that stacks nodes on top of each other.
    Event: An action triggered by the user (like a button click or key press).
    EventHandler: The code that runs in response to an event.
    FXML: A way to define the app's UI structure using an XML file.
    CSS: Used to style the application (colors, fonts, spacing, etc.).
 */

package hellofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
    primaryStage.setTitle("ListAI");
    /** Main menu */

    VBox vbox = new VBox(10);
    
    // Create a header section
    VBox headerSection = new VBox(5);
    headerSection.setAlignment(Pos.CENTER);
    headerSection.setPadding(new Insets(20, 0, 30, 0));
    headerSection.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");

    // Enhanced welcome label
    Label welcome = new Label("Welcome to GroceryNotes");
    welcome.setFont(new Font("Arial Bold", 36));
    welcome.setStyle("-fx-text-fill: #2c3e50;");

    // Add a subtitle
    Label subtitle = new Label("Your Smart Shopping Assistant");
    subtitle.setFont(new Font("Arial", 16));
    subtitle.setStyle("-fx-text-fill: #7f8c8d;");

    headerSection.getChildren().addAll(welcome, subtitle);

    // Button creation
    Button buttonCreateList = new Button();
    buttonCreateList.setText("Create List");
    buttonCreateList.setPrefSize(150,200);
    buttonCreateList.setStyle("-fx-font-size: 14px; -fx-cursor: hand;");

    Button buttonLoadList = new Button();
    buttonLoadList.setPrefSize(150, 200);
    buttonLoadList.setText("Load List");
    buttonLoadList.setStyle("-fx-font-size: 14px; -fx-cursor: hand;");

    // Layout for this page 
    vbox.getChildren().addAll(buttonCreateList, buttonLoadList);
    vbox.setPadding(new Insets(20, 0, 0, 20));  // Add some padding to the button container

    // Layout manager creation
    BorderPane mainLayout = new BorderPane();
    mainLayout.setTop(headerSection);
    mainLayout.setLeft(vbox);
    mainLayout.setStyle("-fx-background-color: white;");

    // Scene creation for main menu
    Scene mainScene = new Scene(mainLayout, 500, 500);








    
    /** Create list page */
    // Layout creation
    AnchorPane createListLayout = new AnchorPane();

    // Button creation
    Button C_backButton = new Button();
    C_backButton.setText("Back to main menu");
    C_backButton.setPrefSize(120,30);
    C_backButton.setStyle("-fx-cursor: hand;");
    AnchorPane.setLeftAnchor(C_backButton, 10.0);
    AnchorPane.setBottomAnchor(C_backButton, 10.0);


    Button saveButton = new Button();
    saveButton.setText("Save list");
    saveButton.setPrefSize(120,30);
    saveButton.setStyle("-fx-cursor: hand;");
    AnchorPane.setRightAnchor(saveButton, 10.0);
    AnchorPane.setBottomAnchor(saveButton, 10.0);
    
    // Label creation
    Label createList = new Label("Write your notes here");
    createList.setFont(new Font ("Arial", 30));
    createList.setPadding(new Insets(0,0,20,0));
    AnchorPane.setTopAnchor(createList, 10.0);
    AnchorPane.setLeftAnchor(createList, 210.0);



    

    createListLayout.getChildren().addAll(C_backButton, saveButton, createList);


    // Scene Creation for create list page 
    Scene createListScene = new Scene(createListLayout, 700,700);





    /** Load list page */

    // Button creation
    Button L_backButton = new Button();
    L_backButton.setText("Back to main menu");
    L_backButton.setStyle("-fx-cursor: hand;");

    // Label manager creation
    StackPane loadListLayout = new StackPane();
    loadListLayout.getChildren().addAll(L_backButton);


    // Scene Creation for create list page 
    Scene loadListScene = new Scene(loadListLayout, 500,500);
    











    /**  Buttons to handle user events such as navigating pages */
    buttonCreateList.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("button pressed");
            primaryStage.setScene(createListScene);// Switch to Create List Page
        }
    });

    buttonLoadList.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent event) {
            System.out.println("button 2 works");
            primaryStage.setScene(loadListScene);
        }
    });

    C_backButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            primaryStage.setScene(mainScene); // Switch back to Main Page
        }
    });

    L_backButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            primaryStage.setScene(mainScene); // Switch back to Main Page
        }
    });

    primaryStage.setScene(mainScene);
    primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}