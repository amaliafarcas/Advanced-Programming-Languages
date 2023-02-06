package Model.ADTStack;

import Exception.MyException;
import Model.Value.Value;

import java.util.ArrayList;
import java.util.List;

public class MyList<Value> implements MyIList<Value> {

    List<Value> list;
    int counter;

    public MyList(ArrayList<Value> list) {
        this.list = list;
        counter = -1;
    }

    public MyList() {
        this.list = new ArrayList<>();
        counter = -1;
    }

    @Override
    public void add(Value v) {
        list.add(v);
    }

    @Override
    public Value getNext() throws MyException {
        if (counter == list.size() - 1) {
            counter++;
            return list.get(counter);
        } else {
            throw new MyException("End of `Out`");
        }
    }


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "OUT = {" + list.toString() + '}';
    }

    @Override
    public List<Value> getList() {
        return list;
    }
}

