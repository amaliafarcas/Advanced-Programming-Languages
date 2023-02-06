/*
import Controller.Controller;
import Model.Expresion.ValueExpression;
import Model.Expresion.VariableExpression;
import Exception.MyException;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static IRepository repository;

    static Controller controller;

    static ArrayList<ProgramState> initialPrgStates;
    static String logFilePath = "demo";

    public static void main(String[] args) {
        System.out.println("Hello world! Test FileTable implementation");


        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement(new StringType(), "varf"),
                         new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("emptyFile"))),
                                 new CompoundStatement(new OpenReadFile(new VariableExpression("varf")),
                                         new CompoundStatement(new VariableDeclarationStatement(new IntType(), "varc"),
                                                 new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                         new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                 new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                         new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CloseReadFile(new VariableExpression("varf"))))))))));
        ProgramState state = new ProgramState(ex4);


        initialPrgStates = new ArrayList<>();
        initialPrgStates.add(state);
        repository = new Repository(initialPrgStates, logFilePath);
        controller = new Controller(repository);

        try {
            controller.allSteps();
        } catch (MyException | IOException exception) {
            System.out.println(exception.getMessage());
        }
        }
}*/
