package Model.ADTStack;

import java.util.HashMap;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value> {

    HashMap<Key, Value> dictionary;

    public MyDictionary(HashMap<Key, Value> dictionary) {
        this.dictionary = dictionary;
    }

    public MyDictionary() {
        this.dictionary = new HashMap<>();
    }

    @Override
    public HashMap<Key, Value> getDictionary() {
        return dictionary;
    }

    @Override
    public boolean isDefined(Key id) {
        return dictionary.get(id) != null;
    }

    @Override
    public Value lookUp(Key id) {
        return dictionary.get(id);
    }

    @Override
    public void update(Key id, Value val) {
        if (isDefined(id)) {
            dictionary.put(id, val);
        }
    }

    @Override
    public void insert(Key id, Value val) {
        dictionary.put(id, val);
    }

    @Override
    public MyIDictionary<Key, Value> deepCopy() {
        MyDictionary<Key, Value> copy = new MyDictionary<Key, Value>();
        for (Key key: this.dictionary.keySet())
            copy.insert(key, dictionary.get(key));
        return copy;
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public String toString() {
        return "SymTable = [" + dictionary.toString() + ']';
    }
}
