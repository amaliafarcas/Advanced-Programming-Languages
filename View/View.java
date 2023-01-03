/*
package View;

import Controller.Controller;
import Exception.MyException;
import Model.Expresion.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;
import Repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    static IRepository repository;

    static Controller controller;

    static ArrayList<ProgramState> initialPrgStates;
    static String logFilePath;

    public static void main(String[] args) throws MyException {

        initialPrgStates = new ArrayList<>();
        setPrgStates();
        System.out.println("Input file name: ");
        Scanner scanner = new Scanner(System. in);
        String logFilePath = scanner.nextLine();
        repository = new Repository(initialPrgStates, logFilePath);
        controller = new Controller(repository);

        int counter = 0;
        ArrayList<Integer> options = new ArrayList<>();
        while (counter <= 3) {
            PrintMenu();
            System.out.println("Option: ");
            Scanner in = new Scanner(System.in);
            int option = Integer.parseInt(in.nextLine());

            if (!options.contains(option) && option != 0) {
                controller.setCurrentProgram(option - 1);
                try {
                    controller.allSteps();
                } catch (MyException | IOException exception) {
                    System.out.println(exception.getMessage());
                }
                System.out.println("\n");
                options.add(option);
                counter++;
            } else if (option != 0) {
                System.out.println("Already executed!\n");
            } else {
                break;
            }
        }
        if(counter==4)
            System.out.println("All programs were executed\n");
    }


    static void setPrgStates() {
        IStatement statement1 = ProgramState1();
        ProgramState state1 = new ProgramState(statement1);
        initialPrgStates.add(state1);

        IStatement statement2 = ProgramState2();
        ProgramState state2 = new ProgramState(statement2);
        initialPrgStates.add(state2);

        IStatement statement3 = ProgramState3();
        ProgramState state3 = new ProgramState(statement3);
        initialPrgStates.add(state3);

        IStatement statement4 = ProgramState4();
        ProgramState state4 = new ProgramState(statement4);
        initialPrgStates.add(state4);
    }

    static void PrintMenu() {
        System.out.println("Choose your program: \n");
        System.out.println("\t0. Exit\n");
        System.out.println("\t1. int v; v=2; Print(v)\n");
        System.out.println("\t2. int a; int b; a=2+3*5; b=a+1; Print(b)\n");
        System.out.println("\t3. bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)\n");
        System.out.println("\t4. bool a; bool b; a=false; b=true; (If a&&b Then print(a) Else print(b))\n");
    }

    static IStatement ProgramState1() {
        //int v; v=2; Print(v)
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "v"), new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
    }

    static IStatement ProgramState2() {
        //int a; int b; a=2+3*5; b=a+1; Print(b)
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(new VariableDeclarationStatement(new IntType(), "b"),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), 3), 1)),
                                new CompoundStatement(new AssignmentStatement("b",
                                        new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), 1)),
                                        new PrintStatement(new VariableExpression("b"))))));
    }

    static IStatement ProgramState3() {
        //bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)
        return new CompoundStatement(new VariableDeclarationStatement(new BoolType(), "a"),
                new CompoundStatement(new VariableDeclarationStatement(new IntType(), "v"),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
    }

    static IStatement ProgramState4() {
        //bool a; bool b; a=false; b=true; (If a&&b Then print(a) Else print(b))
        return new CompoundStatement(new VariableDeclarationStatement(new BoolType(), "a"),
                new CompoundStatement(new VariableDeclarationStatement(new BoolType(), "b"),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(false))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new BoolValue(true))),
                                        new IfStatement(new LogicExpression(new VariableExpression("a"), new VariableExpression("b"), 1), new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b")))))));
    }

}
*/
