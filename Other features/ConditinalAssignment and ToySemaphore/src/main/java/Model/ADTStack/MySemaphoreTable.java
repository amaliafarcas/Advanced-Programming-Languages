package Model.ADTStack;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphoreTable <Integer, Tuple> implements MyISemaphoreTable<Integer, Tuple>{

    private HashMap<Integer, Tuple> semaphoreTable;
    private int nextFreeAddress;

    public MySemaphoreTable() {
        this.semaphoreTable = new HashMap<>();
        this.nextFreeAddress = 100;
    }

    @Override
    public boolean isDefined(Integer id) {
        return semaphoreTable.get(id) != null;
    }

    @Override
    public Tuple lookUp(Integer id) {
        return semaphoreTable.get(id);
    }

    @Override
    public void update(Integer id, Tuple val) {
        if (isDefined(id)) {
            synchronized (this) {semaphoreTable.put(id, val);}
        }
    }

    @Override
    public synchronized HashMap<Integer, Tuple> getSemaphoreTable() {
        synchronized (this) {return this.semaphoreTable;
    }}

    @Override
    public void insert(Integer id, Tuple val) {
        semaphoreTable.put(id, val);
    }

    @Override
    public int getNextFreeAddress() {
        int free = nextFreeAddress;
        nextFreeAddress++;
        return free;
    }

    @Override
    public void setSemaphoreTable(HashMap<Integer, Tuple> semaphoreTable) {
        this.semaphoreTable = semaphoreTable;
    }

    @Override
    public String toString() {
        return "SemaphoreTable=[ " + semaphoreTable +
                ']';
    }
}
