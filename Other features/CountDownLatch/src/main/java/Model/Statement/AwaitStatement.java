package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyILatchTable;
import Model.ADTStack.MyIStack;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import  Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;


public class AwaitStatement implements IStatement{

    String variable;

    public AwaitStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyILatchTable<Integer, Integer> latchTable = state.getLatchTable();

        if (!symTbl.isDefined(variable)) {
            throw new MyException("variable " + variable + "not defined in symtable");
        }

        Type typeId = (symTbl.lookUp(variable)).getType();

        if(!(typeId instanceof IntType)){
            throw new MyException("variable " + variable + "not int");

        }

        IntValue foundIndex = (IntValue) symTbl.lookUp(variable);

        if(!latchTable.isDefined(foundIndex.getValue())){
            throw new MyException("variable " + variable + "not defined in latchTable");
        }

        if(latchTable.lookUp(foundIndex.getValue()) != 0){
            state.getExeStack().push(new AwaitStatement(variable));
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
        return "Await(" + variable +
                ')';
    }
}
