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
    private Database db;

    @Override
    public void start(Stage primaryStage) throws Exception{
    primaryStage.setTitle("App");
        db = new Database();
        db.connect();


    /** ==================== MAIN MENU PAGE ==================== */
    // Create header section
    VBox headerSection = new VBox(5);
    headerSection.setAlignment(Pos.CENTER);
    headerSection.setPadding(new Insets(20, 0, 30, 0));
    headerSection.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");

    Label welcome = new Label("Welcome to GroceryNotes");
    welcome.setFont(new Font("Arial Bold", 36));
    welcome.setStyle("-fx-text-fill: #2c3e50;");

    Label subtitle = new Label("Your Smart Shopping Assistant");
    subtitle.setFont(new Font("Arial", 16));
    subtitle.setStyle("-fx-text-fill: #7f8c8d;");

    headerSection.getChildren().addAll(welcome, subtitle);

    // Main menu buttons
    Button buttonCreateList = new Button("Create List");
    buttonCreateList.setPrefSize(200, 150);
    buttonCreateList.setStyle("-fx-font-size: 18px; "
            + "-fx-cursor: hand; "
            + "-fx-background-color: #4CAF50; "
            + "-fx-text-fill: white; "
            + "-fx-background-radius: 8px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 1);");

    Button buttonLoadList = new Button("Load List");
    buttonLoadList.setPrefSize(200, 150);
    buttonLoadList.setStyle("-fx-font-size: 18px; "
            + "-fx-cursor: hand; "
            + "-fx-background-color: #2196F3; "
            + "-fx-text-fill: white; "
            + "-fx-background-radius: 8px; "
            + "-fx-effecct: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 1);");

    VBox mainButtonsContainer = new VBox(20);
    mainButtonsContainer.setAlignment(Pos.CENTER);
    mainButtonsContainer.setPadding(new Insets(40));
    mainButtonsContainer.getChildren().addAll(buttonCreateList, buttonLoadList);

    BorderPane mainLayout = new BorderPane();
    mainLayout.setTop(headerSection);
    mainLayout.setCenter(mainButtonsContainer);
    mainLayout.setStyle("-fx-background-color: white;");

    Scene mainScene = new Scene(mainLayout, 500, 500);

    /** ==================== CREATE LIST PAGE ==================== */
    // Reuse the header section for a consistent look
    VBox createListHeader = new VBox(5);
    createListHeader.setAlignment(Pos.CENTER);
    createListHeader.setPadding(new Insets(20, 0, 30, 0));
    createListHeader.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");

    Label createListTitle = new Label("Create a New List");
    createListTitle.setFont(new Font("Arial Bold", 36));
    createListTitle.setStyle("-fx       -text-fill: #2c3e50;");

    Label createListSubtitle = new Label("Add your shopping list details below");
    createListSubtitle.setFont(new Font("Arial", 16));
    createListSubtitle.setStyle("-fx-text-fill: #7f8c8d;");

    createListHeader.getChildren().addAll(createListTitle, createListSubtitle);

    // Layout for input fields and buttons
    AnchorPane createListContent = new AnchorPane();
    createListContent.setStyle("-fx-background-color: white;");

    // Label for the main prompt
    Label createList = new Label("Create lists here");
    createList.setFont(new Font("Arial", 30));
    createList.setPadding(new Insets(0, 0, 20, 0));
    AnchorPane.setTopAnchor(createList, 20.0);
    AnchorPane.setLeftAnchor(createList, 20.0);

    // TextField for user input
    TextArea listInputField = new TextArea();
    TextArea listTitle = new TextArea();
    listTitle.setPromptText("Enter your list title here..."); // Placeholder text
    listInputField.setPromptText("Enter your list items here..."); // Placeholder text
    listTitle.setPrefWidth(150);
    listTitle.setPrefHeight(30);
    listInputField.setPrefHeight(300); // Set preferred height
    listInputField.setPrefWidth(200); // Set preferred width
    AnchorPane.setTopAnchor(listTitle, 60.0); // Top margin
    AnchorPane.setLeftAnchor(listTitle, 50.0);
    AnchorPane.setTopAnchor(listInputField, 100.0); // Position below the createList label
    AnchorPane.setLeftAnchor(listInputField, 50.0); // Center it horizontally

    // Back button
    Button C_backButton = new Button("Back to main menu");
    C_backButton.setPrefSize(150, 40);
    C_backButton.setStyle("-fx-font-size: 14px; "
            + "-fx-cursor: hand; "
            + "-fx-background-color: #95a5a6; "
            + "-fx-text-fill: white; "
            + "-fx-background-radius: 5px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 1);");
    AnchorPane.setLeftAnchor(C_backButton, 20.0);
    AnchorPane.setBottomAnchor(C_backButton, 20.0);

    // Save button
    Button saveButton = new Button("Save list");
    saveButton.setPrefSize(150, 40);
    saveButton.setStyle("-fx-font-size: 14px; "
            + "-fx-cursor: hand; "
            + "-fx-background-color: #27ae60; "
            + "-fx-text-fill: white; "
            + "-fx-background-radius: 5px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 1);");
    AnchorPane.setRightAnchor(saveButton, 20.0);
    AnchorPane.setBottomAnchor(saveButton, 20.0);

    // Add all components to the layout
    createListContent.getChildren().addAll(C_backButton, saveButton, listTitle, listInputField);

    // Use a BorderPane to mimic the main menu layout
    BorderPane createListLayout = new BorderPane();
    createListLayout.setTop(createListHeader);
    createListLayout.setCenter(createListContent);
    createListLayout.setStyle("-fx-background-color: white;");

    Scene createListScene = new Scene(createListLayout, 700, 700);

    /** ==================== LOAD LIST PAGE ==================== */
    BorderPane loadListLayout = new BorderPane();
    loadListLayout.setStyle("-fx-background-color: white;");

    Button L_backButton = new Button("Back to main menu");
    L_backButton.setPrefSize(150, 40);
    L_backButton.setStyle("-fx-font-size: 14px; "
            + "-fx-cursor: hand; "
            + "-fx-background-color: #95a5a6; "
            + "-fx-text-fill: white; "
            + "-fx-background-radius: 5px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 1);");
    
    loadListLayout.setBottom(L_backButton);
    BorderPane.setMargin(L_backButton, new Insets(20));
    BorderPane.setAlignment(L_backButton, Pos.CENTER_LEFT);

    Scene loadListScene = new Scene(loadListLayout, 500, 500);

    /** ==================== EVENT HANDLERS ==================== */
    buttonCreateList.setOnAction(event -> {
        primaryStage.setScene(createListScene);
    });

    buttonLoadList.setOnAction(event -> {
        primaryStage.setScene(loadListScene);
    });

    saveButton.setOnAction(event -> {
        String listText = listInputField.getText();
        String titleText = listTitle.getText();
        System.out.println(titleText);
        System.out.println();
        System.out.println(listText);

        String InsertSQL = "INSERT INTO lists(content, title) Values(?, ?)";
        try (var conn = db.connect();
             var pstmt = conn.prepareStatement(InsertSQL)) {
            pstmt.setString(1, listText);
            pstmt.setString(2, titleText);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        listInputField.clear();
        listTitle.clear();
    });

    C_backButton.setOnAction(event -> primaryStage.setScene(mainScene));
    L_backButton.setOnAction(event -> primaryStage.setScene(mainScene));

    /** ==================== START APPLICATION ==================== */
    primaryStage.setScene(mainScene);
    primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}