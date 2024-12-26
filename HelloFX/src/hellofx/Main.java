/** Important terminology
 *  Stage: The window you are using in the application (like the main app window).
    Scene: What’s displayed inside the window (the content of the stage).
    Node: Any element in the UI (like a button, label, or text field).
    Pane: A layout container that organizes nodes (e.g., VBox for vertical alignment, HBox for horizontal alignment).
    VBox: A layout that stacks nodes vertically.
    HBox: A layout that arranges nodes horizontally.
    StackPane: A layout that stacks nodes on top of each other.
    Event: An action triggered by the user (like a button click or key press).
    EventHandler: The code that runs in response to an event.
    FXML: A way to define the app’s UI structure using an XML file.
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
    
    // Button creation
    Button buttonCreateList = new Button();
    buttonCreateList.setText("Create List");
    buttonCreateList.setPrefSize(150,200 );


    Button buttonLoadList = new Button();
    buttonLoadList.setPrefSize(150, 200);
    buttonLoadList.setText("Load List");

    // Label manager creation
    Label welcome = new Label("Welcome to GroceryNotes");
    welcome.setFont(new Font ("Arial", 30));
    welcome.setPadding(new Insets(0,0,20,0));

    // Layout for this page 
    vbox.getChildren().addAll(buttonCreateList, buttonLoadList);

    // Label manager creation
    BorderPane mainLayout = new BorderPane();
    mainLayout.setTop(welcome);
    mainLayout.setLeft(vbox);
    BorderPane.setAlignment(welcome, Pos.CENTER);

    // Scene creation for main menu
    Scene mainScene = new Scene(mainLayout, 500, 500);
    
    /** Create list page */
    // Button creation
    Button backButton = new Button();
    backButton.setText("Back to main menu");

    // Label manager creation
    StackPane createListLayout = new StackPane();
    createListLayout.getChildren().addAll(backButton);


    // Scene Creation for create list page 
    Scene createListScene = new Scene(createListLayout, 500,500);

    
    // Buttons ot handle user events such as navigating pages
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
        }
    });

    backButton.setOnAction(new EventHandler<ActionEvent>() {
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