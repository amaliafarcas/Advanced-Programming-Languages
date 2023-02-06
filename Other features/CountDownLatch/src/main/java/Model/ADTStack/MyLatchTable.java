package Model.ADTStack;

import java.util.HashMap;

public class MyLatchTable <Key, Integer> implements MyILatchTable<Key, Integer>{

    private HashMap<Key, Integer> latchTable;
    private int nextFreeAddress;


    public MyLatchTable() {
        this.latchTable = new HashMap<>();
        this.nextFreeAddress = 100;
    }

    @Override
    public boolean isDefined(Key id) {return latchTable.get(id) != null;
    }

    @Override
    public Integer lookUp(Key id) {
        return latchTable.get(id);
    }

    @Override
    public void update(Key id, Integer val) {
        if (isDefined(id)) {
            synchronized (this) {latchTable.put(id, val);}
        }
    }

    @Override
    public HashMap<Key, Integer> getLatchTable() {
        synchronized (this) {return this.latchTable;
        }
    }

    @Override
    public void insert(Key id, Integer val) {
        latchTable.put(id, val);
    }

    @Override
    public int getNextFreeAddress() {
        int free = nextFreeAddress;
        nextFreeAddress++;
        return free;
    }

    @Override
    public void setLatchTable(HashMap<Key, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    @Override
    public String toString() {
        return "MyLatchTable = [" + latchTable +
                ']';
    }
}
