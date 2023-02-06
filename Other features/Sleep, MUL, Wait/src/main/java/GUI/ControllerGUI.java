package GUI;

import Controller.Controller;
import Model.ProgramState;
import Model.Statement.IStatement;
import Exception.MyException;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGUI implements Initializable {

    ProgramState programState;
    IRepository repository;
    Controller controller;

    @FXML
    TextField numberOfPrograms;

    @FXML
    Button runButton;

    @FXML
    TableView<HashMap.Entry<Integer, Value>> heapTable;
    @FXML
    TableColumn<HashMap.Entry<Integer,Value>, String> heapTableAddress;
    @FXML
    TableColumn<HashMap.Entry<Integer,Value>, String> heapTableValue;
    @FXML
    ListView<String> outputList;
    @FXML
    ListView<String> programStatesList;
    @FXML
    ListView<String> fileTable;
    @FXML
    TableView<HashMap.Entry<String, Value>> symbolTable;
    @FXML
    TableColumn<HashMap.Entry<String, Value>, String> symbolTableVariable;
    @FXML
    TableColumn<HashMap.Entry<String, Value>, String> symbolTableValue;
    @FXML
    ListView<String> executionStack;

    public ControllerGUI(IStatement statement) throws MyException {
        programState = new ProgramState(statement);
        ArrayList<ProgramState> repo_st = new ArrayList<>();
        repo_st.add(programState);
        repository = new Repository(repo_st, "logGUI.txt");
        controller = new Controller(repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstTime();
        programStatesList.setOnMouseClicked(e -> setSymbolTableAndExecutionStack());
        runButton.setOnAction(event -> {
            try {
                controller.oneStepGUI();
            } catch (RuntimeException | InterruptedException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Current program alert");
                alert.setHeaderText(null);
//                alert.setContentText("Program successfully finished!");
                alert.setContentText(e.getMessage());
                Button confirm = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
                confirm.setDefaultButton(false);
                confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                alert.showAndWait();
                Stage stage = (Stage) heapTable.getScene().getWindow();
//                stage.close();
            }
//            System.out.println("update");
            updateComponents();
        });
    }

    public void firstTime(){
        setNumberOfPrograms();
        setHeapTable();
        setOutputList();
        setFileTable();
        setProgramStatesList();
        programStatesList.getSelectionModel().selectFirst();
        setSymbolTableAndExecutionStack();
    }

    private void updateComponents() {
        if (repository.getProgramStates().size() != 0) {
            setOutputList();
            setHeapTable();
            setFileTable();
            setSymbolTableAndExecutionStack();
        }
        setNumberOfPrograms();
        setProgramStatesList();
        if (programStatesList.getSelectionModel().getSelectedItems() == null)
            programStatesList.getSelectionModel().selectFirst();

    }

    public void setNumberOfPrograms() {
        numberOfPrograms.setText("" + repository.getProgramStates().size());
        numberOfPrograms.setDisable(true);
    }

    public void setHeapTable() {
        ObservableList<HashMap.Entry<Integer, Value>> heapTableList = FXCollections.observableArrayList();
        heapTableAddress.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
        heapTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        heapTableList.addAll(repository.getProgramStates().get(0).getHeap().getHeap().entrySet());
        heapTable.setItems(heapTableList);
    }

    public void setOutputList() {
        ObservableList<String> output = FXCollections.observableArrayList();
        for (Value value : programState.getOut().getList()) {
            output.add(value.toString());
        }
        outputList.setItems(output);
    }

    public void setProgramStatesList() {
        ObservableList<String> programs = FXCollections.observableArrayList();
        for (ProgramState state: repository.getProgramStates()) {
            programs.add("" + state.getProgramId());
        }
        programStatesList.setItems(programs);
    }

    public void setFileTable() {
        ObservableList<String> files = FXCollections.observableArrayList();
        for (String path: repository.getProgramStates().get(0).getFileTable().getFileTable().keySet()) {
            files.add(path.toString());
        }
        fileTable.setItems(files);
    }

    private void setSymbolTableAndExecutionStack() {
        symbolTable.refresh();
        ObservableList<HashMap.Entry<String, Value>> symTableList = FXCollections.observableArrayList();
        ObservableList<String> exeStackItemsList = FXCollections.observableArrayList();
        symbolTableVariable.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey()));
        symbolTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
//        System.out.println(controller.getProgramStates().get(0).getSymbolsTable());
        List<ProgramState> programs = repository.getProgramStates();
        ProgramState program = null;
        System.out.println(programs);
        for (ProgramState state : programs) {
            if (state.getProgramId() == Integer.parseInt(programStatesList.getSelectionModel().getSelectedItem())) {
                program = state;
                break;
            }
        }
        if (program != null){
            symTableList.addAll(program.getSymTable().getDictionary().entrySet());
//            System.out.println(program.getSymbolsTable().getContent().entrySet());
            for (IStatement statement: program.getStack().getStack()){
                exeStackItemsList.add(statement.toString());
            }
            symbolTable.setItems(symTableList);
            executionStack.setItems(exeStackItemsList);
        }
    }
}
