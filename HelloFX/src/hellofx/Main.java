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

    /**This section is for all the buttons, labels, text areas, etc */


    VBox vbox = new VBox(10);

    Button buttonCreateList = new Button();
    buttonCreateList.setText("Create List");
    buttonCreateList.setPrefSize(150,200 );
    buttonCreateList.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent event) {
            System.out.println("button 1 works");
        }
    });

    Button buttonLoadList = new Button();
    buttonLoadList.setPrefSize(150, 200);
    buttonLoadList.setText("Load List");
    buttonLoadList.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent event) {
            System.out.println("button 2 works");
        }
    });
    
    Label welcome = new Label("Welcome to GroceryNotes");
    welcome.setFont(new Font ("Arial", 30));
    welcome.setPadding(new Insets(0,0,20,0));


    vbox.getChildren().addAll(buttonCreateList, buttonLoadList);
    BorderPane mainLayout = new BorderPane();
    mainLayout.setTop(welcome);
    mainLayout.setLeft(vbox);

    BorderPane.setAlignment(welcome, Pos.CENTER);




    

    Scene scene = new Scene(mainLayout, 500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}