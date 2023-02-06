package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyISemaphoreTable;
import Model.ADTStack.MyIStack;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.util.ArrayList;


public class CreateSemaphore implements IStatement{

    String variable;
    Expression expression;

    public CreateSemaphore(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyISemaphoreTable<Integer, Pair<Integer, ArrayList<Integer>>> semaphoreTable = state.getSemaphoreTable();

        if (!symTbl.isDefined(variable)){
            throw new MyException("Variable " + variable + "not defined on symTable");
        }

        Type typeId = (symTbl.lookUp(variable)).getType();

        if (!(typeId instanceof IntType)){
            throw new MyException("the used variable " + variable + " does not have Int type");
        }

        IntValue foundIndex = (IntValue) symTbl.lookUp(variable);

        Value value1 = expression.evaluate(symTbl, heap);
        Type type1 = value1.getType();

        if (!type1.equals(new IntType())){
            throw new MyException("Expression" + expression + "not of type int");
        }

        int newFreeLocation = semaphoreTable.getNextFreeAddress();

        IntValue number1 = (IntValue) value1;
        Pair<Integer,ArrayList<Integer>> pair = new  Pair<>(number1.getValue(), new ArrayList<>());
        System.out.println(pair);
        semaphoreTable.insert(newFreeLocation, pair);

        symTbl.update(variable, new IntValue(newFreeLocation));

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeVariable  = typeEnvironment.lookUp(variable);

        if (!(typeVariable instanceof IntType)){
            throw new MyException("the used variable " + variable + " does not have Int type");
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "CreateSemaphore(" + variable + ", " +
              expression + ')';
    }
}
