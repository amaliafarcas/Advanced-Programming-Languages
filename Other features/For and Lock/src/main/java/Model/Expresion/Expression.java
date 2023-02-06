package Model.Expresion;

import Model.ADTStack.MyIDictionary;
import Exception.MyException;
import Model.ADTStack.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public interface Expression {
    Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> heap) throws MyException;

    Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws  MyException;
}
