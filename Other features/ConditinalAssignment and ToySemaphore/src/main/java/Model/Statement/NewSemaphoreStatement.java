package Model.Statement;

import Exception.MyException;
import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyISemaphoreTable;
import Model.ADTStack.MyIStack;
import Model.Expresion.Expression;
import Model.Expresion.ValueExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.util.ArrayList;

public class NewSemaphoreStatement implements IStatement {

    String variable;
    Expression expression1;
    Expression expression2;

    public NewSemaphoreStatement(String variable, Expression expression1, Expression expression2) {
        this.variable = variable;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public void setExpression1(Expression expression1) {
        this.expression1 = expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }

    public void setExpression2(Expression expression2) {
        this.expression2 = expression2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyISemaphoreTable<Integer, Pair<Integer,Pair<ArrayList<Integer>, Integer>>> semaphoreTable = state.getSemaphoreTable();

        if (symTbl.isDefined(variable)) {
            Type typeId = (symTbl.lookUp(variable)).getType();

            if (typeId instanceof IntType) {

                IntValue foundIndex = (IntValue) symTbl.lookUp(variable);

                Value value1 = expression1.evaluate(symTbl, heap);
                Type type1 = value1.getType();

                Value value2 = expression2.evaluate(symTbl, heap);
                Type type2 = value2.getType();

                if (type1.equals(new IntType()) && type2.equals(new IntType())) {

                    int newFreeLocation = semaphoreTable.getNextFreeAddress();

                    IntValue number1 = (IntValue) value1;
                    IntValue number2 = (IntValue) value2;
                    Pair<ArrayList<Integer>, Integer> insidePair = new Pair<>(new ArrayList<>(), number2.getValue());
                    Pair<Integer,Pair<ArrayList<Integer>, Integer>> triple = new  Pair<>(number1.getValue(), insidePair);
                    System.out.println(triple);
                    semaphoreTable.insert(newFreeLocation, triple);
                    symTbl.update(variable, new IntValue(newFreeLocation));


                    } else {
                        throw new MyException("Expression" + expression1 + " and/or " + expression2 + "not of type int");
                    }
                } else {
                    throw new MyException("the used variable " + variable + " does not have Int type");
                }
            } else {
                throw new MyException("the used variable " + variable + " was not declared before");
            }
            return null;
        }

        @Override public MyIDictionary<String, Type> typeCheck (MyIDictionary < String, Type > typeEnvironment) throws
        MyException {
            Type typeVariable  = typeEnvironment.lookUp(variable);

            if (!(typeVariable instanceof IntType)){
                throw new MyException("the used variable " + variable + " does not have Int type");
            }
            return typeEnvironment;
        }

    @Override
    public String toString() {
        return "NewSemaphore(" + variable +
                ", " + expression1 +
                ", " + expression2 +
                ')';
    }
}
