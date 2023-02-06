package Model.ADTStack;

import java.util.HashMap;

public class MyHeap<Integer, Value> implements MyIHeap<Integer, Value> {

    private HashMap<Integer, Value> heap;
    private int nextFreeAddress;

    public MyHeap() {
        this.heap = new HashMap<>();
        this.nextFreeAddress = 1;
    }

    @Override
    public boolean isDefined(Integer id) {
        return heap.get(id) != null;
    }

    @Override
    public Value lookUp(Integer id) {
        return heap.get(id);
    }

    @Override
    public void update(Integer id, Value val) {
        if (isDefined(id)) {
            heap.put(id, val);
        }
    }

/*    @Override
    public Integer getAddress(Value value) {
        Integer key = null;
        if(!this.heap.isEmpty())
            key = 1;
        for(HashMap.Entry<Integer, Value> entry : heap.entrySet())
            if(entry.getValue().equals(value))
                key = (Integer)entry.getKey();

        return (Integer) key;
    }*/

    @Override
    public void setHeap(HashMap<Integer, Value> heap) {
        this.heap = heap;
    }

    @Override
    public HashMap<Integer, Value> getHeap() {
        return heap;
    }

    @Override
    public void insert(Integer address, Value val) {
        //System.out.println(address);
        heap.put(address, val);
    }

    @Override
    public int getNextFreeAddress() {
        int free = nextFreeAddress;
        nextFreeAddress++;
        return free;
    }

    @Override
    public String toString() {
        return "MyHeap=[ "  + heap.toString() +
                ']';
    }
}
