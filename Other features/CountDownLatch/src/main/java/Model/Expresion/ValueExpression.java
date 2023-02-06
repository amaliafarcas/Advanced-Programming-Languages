package Model.Expresion;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable,  MyIHeap<Integer, Value> heap) {
        return value;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
