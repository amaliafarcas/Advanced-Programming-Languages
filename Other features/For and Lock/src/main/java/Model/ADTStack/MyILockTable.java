package Model.ADTStack;

import java.util.HashMap;

public interface MyILockTable<Index, Identifier>{

    boolean isDefined(Integer id);

    Identifier lookUp(Integer id);

    void update(Integer id, Identifier val);

    //public void clear();

    HashMap<Integer, Identifier> getLockTable();

    void insert(Integer id, Identifier val);

    int getNextFreeAddress();

    // T1 getAddress(T2 value);

    void setLockTable(HashMap<Integer, Identifier> heap);
}
