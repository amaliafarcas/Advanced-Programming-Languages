package Model.ADTStack;

import java.util.HashMap;
import java.util.Map;

public interface MyIDictionary<Key, Value> {
    boolean isDefined(Key id);

    Value lookUp(Key id);

    void update(Key id, Value val);

    //public void clear();

    HashMap<Key, Value> getDictionary();

    void insert(Key id, Value val);


    MyIDictionary<Key, Value> deepCopy();

    //public Value remove(String id);

    //public Value removeAny();

    int size();

}
