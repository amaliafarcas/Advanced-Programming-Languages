package Model.ADTStack;

import java.util.HashMap;

public interface MyIFileTable <String, BfReader>{
    boolean isDefined(String id);

    BfReader lookUp(String id);

    void update(String id, BfReader val);

    void insert(String id, BfReader val);

    void delete(String s);

    HashMap<String, BfReader> getFileTable();

    int size();
}
