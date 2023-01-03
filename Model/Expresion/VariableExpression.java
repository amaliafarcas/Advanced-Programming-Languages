package Model.Expresion;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class VariableExpression implements Expression {

    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable,  MyIHeap<Integer, Value> heap) {
        return symTable.lookUp(name);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return typeEnvironment.lookUp(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
