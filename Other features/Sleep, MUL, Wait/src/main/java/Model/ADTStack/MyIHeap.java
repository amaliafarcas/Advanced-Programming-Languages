package Model.ADTStack;

import java.util.HashMap;

public interface MyIHeap<Integer, Value>{

    boolean isDefined(Integer id);

    Value lookUp(Integer id);

    void update(Integer id, Value val);

    //public void clear();

    HashMap<Integer, Value> getHeap();

    void insert(Integer id, Value val);

    int getNextFreeAddress();

   // T1 getAddress(T2 value);

    void setHeap(HashMap<Integer, Value> heap);
}
