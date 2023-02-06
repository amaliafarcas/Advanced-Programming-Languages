package com.example.ass7toyinterpreter;

import Controller.Controller;
import GUI.ControllerGUI;
import Model.ProgramState;
import Model.Statement.IStatement;
import Repository.Repository;
import View.Interpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Exception.MyException;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<IStatement> statements;

    @FXML
    private Button selectProgram;

    ArrayList<Controller> controllers_list;
    ArrayList<IStatement> statements_list;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLists();
        ObservableList<IStatement> cont = FXCollections.observableArrayList();
        cont.addAll(statements_list);

        statements.setItems(cont);
        System.out.println("1");
        //System.out.println(controllers_list);
        selectProgram.setOnAction(e -> {try {
                Stage run_program = new Stage();
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RunProgramWindow.fxml"));
                ControllerGUI run_window = new ControllerGUI(statements.getSelectionModel().getSelectedItem());
                loader.setController(run_window);


                root = loader.load();
                Scene scene = new Scene(root, 1200, 780);
                run_program.setTitle("Program state details");
                run_program.setScene(scene);
                run_program.show();
            } catch (IOException | MyException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @FXML
    public void setupLists(){
        System.out.println("setup");
        statements_list = new ArrayList<>();
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
        controllers_list = new ArrayList<>();
        for (IStatement statement : statements_list) {
            try {
                ProgramState program = new ProgramState(statement);
                ArrayList<ProgramState> repo_st = new ArrayList<>();
                repo_st.add(program);
                var repo = new Repository((List<ProgramState>) repo_st, "log_GUI.txt");
                var cont = new Controller(repo);

                controllers_list.add(cont);
            } catch (RuntimeException | MyException e) {
//                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Current statement alert: " + statement.toString());
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                confirm.setDefaultButton(false);
                confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                alert.showAndWait();
            }

        }
    }

}