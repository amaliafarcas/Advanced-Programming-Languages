package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyILatchTable;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;


public class NewLatchStatement implements IStatement{

    String variable;
    Expression expression;

    public NewLatchStatement(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyILatchTable<Integer, Integer> latchTable = state.getLatchTable();

        Value value = expression.evaluate(symTbl, heap);

        if(!value.getType().equals(new IntType())){
            throw new MyException("Expression " + expression + " not int");
        }

        int freeLocation = latchTable.getNextFreeAddress();
        IntValue number = (IntValue) value;
        latchTable.insert(freeLocation, number.getValue());

        if(!symTbl.isDefined(variable)){
            throw new MyException("Variable " + variable + " not defined in symTable");
        }

        symTbl.update(variable, new IntValue(freeLocation));


        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {

        Type typeVariable  = typeEnvironment.lookUp(variable);

        if (!(typeVariable instanceof IntType)){
            throw new MyException("the used variable " + variable + " does not have Int type");
        }

        Type typeExpression = expression.typeCheck(typeEnvironment);

        if(!typeExpression.equals(new IntType())){
            throw new MyException("Expression " + expression + " not int");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "NewLatch(" + variable +
                ", " + expression +
                ')';
    }
}
