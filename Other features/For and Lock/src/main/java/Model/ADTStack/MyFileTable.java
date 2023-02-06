package Model.ADTStack;
import java.util.HashMap;

public class MyFileTable<String, BfReader> implements MyIFileTable<String, BfReader>{

    HashMap<String, BfReader> fileTableDic;

    public MyFileTable(HashMap<String, BfReader> fileTableDic) {
        this.fileTableDic = fileTableDic;
    }

    public MyFileTable() {
        this.fileTableDic = new HashMap<>();
    }

    public HashMap<String, BfReader> getFileTableDic() {
        return fileTableDic;
    }

    public void setFileTableDic(HashMap<String, BfReader> fileTableDic) {
        this.fileTableDic = fileTableDic;
    }

    @Override
    public boolean isDefined(String id) {return fileTableDic.get(id) != null;
    }

    @Override
    public BfReader lookUp(String id) {return fileTableDic.get(id);
    }

    @Override
    public void update(String id, BfReader NewVal) {
        if (isDefined(id)) {
            fileTableDic.put(id, NewVal);
        }
    }

    @Override
    public void insert(String id, BfReader val) {
        fileTableDic.put(id, val);
    }

    @Override
    public void delete(String s) {
        fileTableDic.remove(s);
    }

    @Override
    public HashMap<String, BfReader> getFileTable() {
        return fileTableDic;
    }

    @Override
    public int size() {
        return fileTableDic.size();
    }


    @Override
    public java.lang.String toString() {
        return "MyFileTable = [" + fileTableDic.toString() +
                ']';
    }
}
