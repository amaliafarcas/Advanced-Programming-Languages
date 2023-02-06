package View;

import Controller.Controller;
import Model.ADTStack.MyDictionary;
import Model.Expresion.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;
import Exception.MyException;

import java.util.ArrayList;

public class Interpreter {
    public static IStatement getSt1(){
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "v"), new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
    }

    public static IStatement getSt2(){
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "a"), new CompoundStatement(new VariableDeclarationStatement(new IntType(), "b"), new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), 3), 1)), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), 1)), new PrintStatement(new VariableExpression("b"))))));

    }

    public static IStatement getSt3(){
        return new CompoundStatement(new VariableDeclarationStatement(new BoolType(), "a"), new CompoundStatement(new VariableDeclarationStatement(new IntType(), "v"), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))), new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
    }

    public static IStatement getSt4(){
        return new CompoundStatement(new VariableDeclarationStatement(new BoolType(), "a"), new CompoundStatement(new VariableDeclarationStatement(new BoolType(), "b"), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(false))), new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new BoolValue(true))), new IfStatement(new LogicExpression(new VariableExpression("a"), new VariableExpression("b"), 1), new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b")))))));
    }

    public static IStatement getSt5(){
        return new CompoundStatement(new VariableDeclarationStatement(new StringType(), "varf"), new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))), new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")), new CompoundStatement(new VariableDeclarationStatement(new IntType(), "varc"), new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CloseReadFileStatement(new VariableExpression("varf"))))))))));
    }

    public static IStatement getSt6(){
        return new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new IntType()), "v"), new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))), new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new ReferenceType(new IntType())), "a"), new CompoundStatement(new NewStatement("a", new VariableExpression("v")), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
    }

    public static IStatement getSt7(){
        return new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new IntType()), "v"), new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))), new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new ReferenceType(new IntType())), "a"), new CompoundStatement(new NewStatement("a", new VariableExpression("v")), new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))), new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression((new VariableExpression("a")))), new ValueExpression(new IntValue(5)), 1)))))));
    }

    public static IStatement getSt8(){
        return new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new IntType()), "v"), new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))), new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))), new CompoundStatement(new WriteHeapStatement(new VariableExpression("v"), new ValueExpression(new IntValue(30))), new PrintStatement(new ArithmeticExpression(new ReadHeapExpression((new VariableExpression("v"))), new ValueExpression(new IntValue(5)), 1))))));
    }

    public static IStatement getSt9(){
        return new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new IntType()), "v"), new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))), new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new ReferenceType(new IntType())), "a"), new CompoundStatement(new NewStatement("a", new VariableExpression("v")), new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))), new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
    }

    public static IStatement getSt10(){
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "v"), new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))), new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 2)))), new PrintStatement(new VariableExpression("v")))));
    }

    public static IStatement getSt11(){
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "v"), new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new IntType()), "a"), new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))), new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))), new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement(new VariableExpression("a"), new ValueExpression(new IntValue(30))), new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
    }

    public static IStatement getSt12(){
        return new CompoundStatement(new VariableDeclarationStatement(new IntType(), "a"), new CompoundStatement(new VariableDeclarationStatement(new IntType(), "b"), new CompoundStatement(new VariableDeclarationStatement(new IntType(), "c"), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(1))), new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(2))), new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(5))), new CompoundStatement(new SwitchStatement(new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(10)), 3), new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("c"), 3), new ValueExpression(new IntValue(10)), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))), new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))), new PrintStatement(new ValueExpression(new IntValue(200)))), new PrintStatement(new ValueExpression(new IntValue(300)))), new PrintStatement(new ValueExpression(new IntValue(300))))))))));
    }

    public static IStatement getSt13(){
        return new CompoundStatement(new VariableDeclarationStatement(new ReferenceType(new IntType()), "v1"), new CompoundStatement(new VariableDeclarationStatement(new IntType(), "cnt"), new CompoundStatement(new NewStatement("v1", new ValueExpression(new IntValue(1))), new CompoundStatement(new CreateSemaphore("cnt", new ReadHeapExpression(new VariableExpression("v1"))), new CompoundStatement(new ForkStatement(new CompoundStatement(new AcquireStatement("cnt"), new CompoundStatement(new WriteHeapStatement(new VariableExpression("v1"), new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)), new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))), new ReleaseStatement("cnt"))))), new CompoundStatement(new ForkStatement(new CompoundStatement(new AcquireStatement("cnt"), new CompoundStatement(new WriteHeapStatement(new VariableExpression("v1"), new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)), new CompoundStatement(new WriteHeapStatement(new VariableExpression("v1"), new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(2)), 3)), new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))), new ReleaseStatement("cnt")))))), new CompoundStatement(new AcquireStatement("cnt"), new CompoundStatement(new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)), 2)), new ReleaseStatement("cnt")))))))));
    }


    public static void main(String[] args) throws MyException {

        IStatement statement1 = getSt1();

        statement1.typeCheck(new MyDictionary<String, Type>());
        ProgramState program1 = new ProgramState(statement1);
        ArrayList<ProgramState> initialPrgState1 = new ArrayList<>();
        initialPrgState1.add(program1);
        IRepository repository1 = new Repository(initialPrgState1, "log1.txt");
        Controller controller1 = new Controller(repository1);


        IStatement statement2 = getSt2();

        statement2.typeCheck(new MyDictionary<String, Type>());
        ProgramState program2 = new ProgramState(statement2);
        ArrayList<ProgramState> initialPrgState2 = new ArrayList<>();
        initialPrgState2.add(program2);
        IRepository repository2 = new Repository(initialPrgState2, "log2.txt");
        Controller controller2 = new Controller(repository2);


        IStatement statement3 = getSt3();

        statement3.typeCheck(new MyDictionary<String, Type>());
        ProgramState program3 = new ProgramState(statement3);
        ArrayList<ProgramState> initialPrgState3 = new ArrayList<>();
        initialPrgState3.add(program3);
        IRepository repository3 = new Repository(initialPrgState3, "log3.txt");
        Controller controller3 = new Controller(repository3);


        IStatement statement4 = getSt4();

        statement4.typeCheck(new MyDictionary<String, Type>());
        ProgramState program4 = new ProgramState(statement4);
        ArrayList<ProgramState> initialPrgState4 = new ArrayList<>();
        initialPrgState4.add(program4);
        IRepository repository4 = new Repository(initialPrgState4, "log4.txt");
        Controller controller4 = new Controller(repository4);


        IStatement statementTestFile = getSt5();

        statementTestFile.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestFile = new ProgramState(statementTestFile);
        ArrayList<ProgramState> initialPrgStateTestFile = new ArrayList<>();
        initialPrgStateTestFile.add(programTestFile);
        IRepository repositoryTestFile = new Repository(initialPrgStateTestFile, "logTestFile.txt");
        Controller controllerTestFile = new Controller(repositoryTestFile);


        IStatement statementTestHeapAllocation = getSt6();

        statementTestHeapAllocation.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestHeapAllocation = new ProgramState(statementTestHeapAllocation);
        ArrayList<ProgramState> initialPrgStateTestHeapAllocation = new ArrayList<>();
        initialPrgStateTestHeapAllocation.add(programTestHeapAllocation);
        IRepository repositoryTestHeapAllocation = new Repository(initialPrgStateTestHeapAllocation, "logTestHeapAllocation.txt");
        Controller controllerTestHeapAllocation = new Controller(repositoryTestHeapAllocation);


        IStatement statementTestHeapReading = getSt7();

        statementTestHeapReading.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestHeapReading = new ProgramState(statementTestHeapReading);
        ArrayList<ProgramState> initialPrgStateTestHeapReading = new ArrayList<>();
        initialPrgStateTestHeapReading.add(programTestHeapReading);
        IRepository repositoryTestHeapReading = new Repository(initialPrgStateTestHeapReading, "logTestHeapReading.txt");
        Controller controllerTestHeapReading = new Controller(repositoryTestHeapReading);


        IStatement statementTestHeapWriting = getSt8();

        statementTestHeapWriting.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestHeapWriting = new ProgramState(statementTestHeapWriting);
        ArrayList<ProgramState> initialPrgStateTestHeapWriting = new ArrayList<>();
        initialPrgStateTestHeapWriting.add(programTestHeapWriting);
        IRepository repositoryTestHeapWriting = new Repository(initialPrgStateTestHeapWriting, "logTestHeapWriting.txt");
        Controller controllerTestHeapWriting = new Controller(repositoryTestHeapWriting);


        IStatement statementTestGarbageCollector = getSt9();

        statementTestGarbageCollector.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestGarbageCollector = new ProgramState(statementTestGarbageCollector);
        ArrayList<ProgramState> initialPrgStateTestGarbageCollector = new ArrayList<>();
        initialPrgStateTestGarbageCollector.add(programTestGarbageCollector);
        IRepository repositoryTestGarbageCollector = new Repository(initialPrgStateTestGarbageCollector, "logTestGarbageCollector.txt");
        Controller controllerTestGarbageCollector = new Controller(repositoryTestGarbageCollector);


        IStatement statementTestWhileStatement = getSt10();

        statementTestWhileStatement.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestWhileStatement = new ProgramState(statementTestWhileStatement);
        ArrayList<ProgramState> initialPrgStateTestWhileStatement = new ArrayList<>();
        initialPrgStateTestWhileStatement.add(programTestWhileStatement);
        IRepository repositoryTestWhileStatement = new Repository(initialPrgStateTestWhileStatement, "logTestWhileStatement.txt");
        Controller controllerTestWhileStatement = new Controller(repositoryTestWhileStatement);


        IStatement statementTestOneStepForAll = getSt11();

        statementTestOneStepForAll.typeCheck(new MyDictionary<String, Type>());
        ProgramState programTestOneStepForAll = new ProgramState(statementTestOneStepForAll);
        ArrayList<ProgramState> initialPrgStateTestOneStepForAll = new ArrayList<>();
        initialPrgStateTestOneStepForAll.add(programTestOneStepForAll);
        IRepository repositoryTestOneStepForAll = new Repository(initialPrgStateTestOneStepForAll, "logTestOneStepForAll.txt");
        Controller controllerTestOneStepForAll = new Controller(repositoryTestOneStepForAll);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", statement1.toString(), controller1));
        menu.addCommand(new RunExample("2", statement2.toString(), controller2));
        menu.addCommand(new RunExample("3", statement3.toString(), controller3));
        menu.addCommand(new RunExample("4", statement4.toString(), controller4));
        menu.addCommand(new RunExample("5", statementTestFile.toString(), controllerTestFile));
        menu.addCommand(new RunExample("6", statementTestHeapAllocation.toString(), controllerTestHeapAllocation));
        menu.addCommand(new RunExample("7", statementTestHeapReading.toString(), controllerTestHeapReading));
        menu.addCommand(new RunExample("8", statementTestHeapWriting.toString(), controllerTestHeapWriting));
        menu.addCommand(new RunExample("9", statementTestGarbageCollector.toString(), controllerTestGarbageCollector));
        menu.addCommand(new RunExample("10", statementTestWhileStatement.toString(), controllerTestWhileStatement));
        menu.addCommand(new RunExample("11", statementTestOneStepForAll.toString(), controllerTestOneStepForAll));


        menu.show();

    }
}
