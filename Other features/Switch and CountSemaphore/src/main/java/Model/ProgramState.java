package Model;

import Model.ADTStack.*;
import Model.Statement.IStatement;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIFileTable<String, BufferedReader> fileTable;
    MyIHeap<Integer, Value> heap;

    MyISemaphoreTable<Integer, Pair<Integer, ArrayList<Integer>>> semaphoreTable;
    IStatement originalProgram;

    private static int globalProgramId = 0;
    int programId;


    public ProgramState(MyIStack<IStatement> exeStack,
                        MyIDictionary<String, Value> symTable,
                        MyIList<Value> out,
                        MyIFileTable<String, BufferedReader> fileTable, MyIHeap<Integer, Value> heap,
                        MyISemaphoreTable<Integer, Pair<Integer, ArrayList<Integer>>> semaphoreTable,
                        IStatement originalProgram) throws MyException {

        /*try {
            originalProgram.typeCheck(new MyDictionary<String, Type>());
        }
        catch (MyException e){
            throw new MyException(e.toString());
        }*/
        this.programId = generateId();
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram;
        this.semaphoreTable = semaphoreTable;
        exeStack.push(originalProgram);
    }

    public ProgramState(IStatement originalProgram) throws MyException {
        /*try {
            originalProgram.typeCheck(new MyDictionary<String, Type>());
        }
        catch (MyException e){
            throw new MyException(e.toString());
        }*/
        this.programId = generateId();
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.out = new MyList<>();
        this.fileTable = new MyFileTable<>();
        exeStack.push(originalProgram);
        this.heap = new MyHeap<>();
        this.semaphoreTable = new MySemaphoreTable<>();
        this.originalProgram = originalProgram;
    }

    public MyIFileTable<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIFileTable<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Integer, Value> heap) {
        this.heap = heap;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyISemaphoreTable<Integer, Pair<Integer, ArrayList<Integer>>> getSemaphoreTable() {
        return semaphoreTable;
    }

    public void setSemaphoreTable(MyISemaphoreTable<Integer, Pair<Integer, ArrayList<Integer>>> semaphoreTable) {
        this.semaphoreTable = semaphoreTable;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public int getProgramId() {
        return programId;
    }

    @Override
    public String toString() {
        return "Program State: \n\t Id: " + programId +
                "\n\t" + exeStack.toString() +
                "\n\t" + symTable.toString() +
                "\n\t" + out.toString() +
                "\n\t" + fileTable.toString() +
                "\n\t" + heap.toString() +"\n\t" + semaphoreTable.toString() +
                "\n\t" + "\n\tOriginalProgram = [" + originalProgram.toString() + "]" + "\n\n\n";
    }

    public MyIStack<IStatement> getStack() {
        return exeStack;
    }

    public boolean isNotCompleted(){
        //System.out.println(exeStack.toString());
        return !exeStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException {
        if(exeStack.isEmpty()){
            throw new MyException("programState stack is empty");
        }
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    private synchronized int generateId(){
        globalProgramId+=1;
        return globalProgramId;
    }
}
