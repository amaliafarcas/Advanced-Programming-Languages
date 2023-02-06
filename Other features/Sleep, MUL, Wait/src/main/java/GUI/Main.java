package GUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Exception.*;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws MyException, IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProgramsList.fxml")));
        stage.setTitle("Select a program");
        stage.setScene(new Scene(root, 600, 420));
        stage.show();

        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProgramsList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 420);
        stage.setTitle("Select a program");
        stage.setScene(scene);
        stage.show();*/
    }
}
