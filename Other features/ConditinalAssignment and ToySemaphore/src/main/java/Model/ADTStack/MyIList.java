package Model.ADTStack;

import Exception.MyException;
import Model.Value.Value;

import java.util.ArrayList;
import java.util.List;

public interface MyIList<Value> {

    void add(Value v);

    //boolean remove(Val v);
    Value getNext() throws MyException;

    int size();

    String toString();

   List<Value> getList();
}
