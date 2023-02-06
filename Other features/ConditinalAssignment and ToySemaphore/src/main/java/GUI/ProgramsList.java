package GUI;

import Model.ProgramState;
import Model.Statement.*;
import Repository.Repository;
import Controller.Controller;
import View.Interpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import Exception.MyException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class ProgramsList implements Initializable {

    @FXML
    private ListView<IStatement> statements;

    @FXML
    private Button selectProgram;

    ArrayList<IStatement> statements_list;

    public ProgramsList() {
        this.statements_list = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLists();

        ObservableList<IStatement> stm = FXCollections.observableArrayList();
        stm.addAll(statements_list);
        statements.setItems(stm);
//        System.out.println(controllers_list);
        selectProgram.setOnAction(e -> {
            try {
                    Stage stage = new Stage();
                    Parent root;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ControllerGUI.fxml"));
                    ControllerGUI run_window = new ControllerGUI(statements.getSelectionModel().getSelectedItem());
                    loader.setController(run_window);

                    root = loader.load();
                    Scene scene = new Scene(root, 720, 510);
                    stage.setTitle("Program state details");
                    stage.setScene(scene);
                    stage.show();

            } catch (IOException | MyException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @FXML
    public void setupLists(){

        statements_list.add(Interpreter.getSt1());
        statements_list.add(Interpreter.getSt2());
        statements_list.add(Interpreter.getSt3());
        statements_list.add(Interpreter.getSt4());
        statements_list.add(Interpreter.getSt5());
        statements_list.add(Interpreter.getSt6());
        statements_list.add(Interpreter.getSt7());
        statements_list.add(Interpreter.getSt8());
        statements_list.add(Interpreter.getSt9());
        statements_list.add(Interpreter.getSt10());
        statements_list.add(Interpreter.getSt11());
        statements_list.add(Interpreter.getSt12());
        statements_list.add(Interpreter.getSt13());




        /*for (IStatement statement : statements_list) {
            var repo = new Repository((List<ProgramState>) statement, "log_GUI.txt");
            var cont = new Controller(repo);
            try {
                controllers_list.add(cont);
            } catch (RuntimeException e) {
//                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Current statement alert: " + statement.toString());
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                confirm.setDefaultButton(false);
                confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                alert.showAndWait();
            }*/

        }
}
