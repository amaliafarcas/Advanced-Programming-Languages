package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyISemaphoreTable;
import Model.ADTStack.MyIStack;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.util.ArrayList;


public class AcquireStatement implements IStatement{

    String variable;

    public AcquireStatement(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyISemaphoreTable<Integer, Pair<Integer,ArrayList<Integer>>> semaphoreTable = state.getSemaphoreTable();

        if (symTbl.isDefined(variable)) {
            Type typeId = (symTbl.lookUp(variable)).getType();

            if (typeId instanceof IntType) {

                IntValue foundIndex = (IntValue) symTbl.lookUp(variable);



                if(semaphoreTable.isDefined(foundIndex.getValue())){

                    Pair<Integer,ArrayList<Integer>> pair = semaphoreTable.lookUp(foundIndex.getValue());

                    ArrayList<Integer> list1 = pair.getValue();

                    int lengthList1 = list1.size();

                    System.out.println(pair.getKey());
                    System.out.println(pair.getValue());
                    System.out.println(lengthList1);
                    if (pair.getKey() > lengthList1){

                        if(!list1.contains(state.getProgramId())){
                            list1.add(state.getProgramId());
                        }
                    }
                    else{
                        state.getExeStack().push(new AcquireStatement(variable));
                    }

                }
                else{
                    throw new MyException("the used variable " + variable + " is not defined in the semaphore Table");
                }

            } else {
                throw new MyException("the used variable " + variable + " does not have Int type");
            }
        } else {
            throw new MyException("the used variable " + variable + " was not declared before");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeVariable  = typeEnvironment.lookUp(variable);

        if (!(typeVariable instanceof IntType)){
            throw new MyException("the used variable " + variable + " does not have Int type");
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Acquire(" + variable +
                ')';
    }
}
