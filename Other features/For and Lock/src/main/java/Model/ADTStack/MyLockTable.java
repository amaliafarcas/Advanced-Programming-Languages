package Model.ADTStack;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockTable<Index, Identifier> implements MyILockTable<Index, Identifier>{
    private HashMap<Integer, Identifier> lockTable;
    private int nextFreeAddress;
    private final ReentrantLock tableLock;

    public MyLockTable() {
        this.lockTable = new HashMap<>();
        nextFreeAddress = 100;
        this.tableLock = new ReentrantLock();
        }

    @Override
    public boolean isDefined(Integer id) {
        return lockTable.get(id) != null;
    }

    @Override
    public Identifier lookUp(Integer id) {
        return lockTable.get(id);
    }

    @Override
    public void update(Integer id, Identifier val) {
        tableLock.lock();
        if (isDefined(id)) {
            lockTable.put(id, val);
        }
        tableLock.unlock();
    }

    @Override
    public synchronized HashMap<Integer, Identifier> getLockTable() {
        return lockTable;
    }

    @Override
    public void insert(Integer id, Identifier val) {
        tableLock.lock();
        lockTable.put(id, val);
        tableLock.unlock();
    }

    @Override
    public int getNextFreeAddress() {
        tableLock.lock();
        int free = nextFreeAddress;
        nextFreeAddress++;
        tableLock.unlock();
        return free;
    }

    @Override
    public void setLockTable(HashMap<Integer, Identifier> lockTable) {
        tableLock.lock();
        this.lockTable = lockTable;
        tableLock.unlock();
    }

    @Override
    public String toString() {
        return "LockTable= [" + lockTable.toString() +
                ']';
    }
}
