package Model.ADTStack;

import java.util.ArrayList;
import java.util.HashMap;

public interface MyISemaphoreTable<Integer, Tuple>{

    boolean isDefined(Integer id);

    Tuple lookUp(Integer id);

    void update(Integer id, Tuple val);

    //public void clear();

    HashMap<Integer, Tuple> getSemaphoreTable();

    void insert(Integer id, Tuple val);

    int getNextFreeAddress();

    // T1 getAddress(T2 value);

    void setSemaphoreTable(HashMap<Integer, Tuple> heap);
}
