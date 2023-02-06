package Model.ADTStack;

import java.util.HashMap;

public interface MyILatchTable <Key, Integer>{

    boolean isDefined(Key id);

    Integer lookUp(Key id);

    void update(Key id, Integer val);

    HashMap<Key, Integer> getLatchTable();

    void insert(Key id, Integer val);

    int getNextFreeAddress();

    void setLatchTable(HashMap<Key, Integer> heap);
}