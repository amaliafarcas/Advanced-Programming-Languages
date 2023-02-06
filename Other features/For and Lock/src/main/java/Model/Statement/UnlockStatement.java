package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyILockTable;
import Model.ADTStack.MyIStack;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;


public class UnlockStatement implements IStatement{

    String variable;

    public UnlockStatement(String variable) {
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
        MyILockTable<Integer, Integer> lockTable = state.getLockTable();

        if (symTbl.isDefined(variable)) {
            Type typeId = (symTbl.lookUp(variable)).getType();

            if (typeId instanceof IntType) {

                IntValue foundIndex = (IntValue) symTbl.lookUp(variable);

                if(lockTable.isDefined(foundIndex.getValue())){
                    if(foundIndex.getValue()==foundIndex.getValue() && lockTable.lookUp(foundIndex.getValue()) == state.getProgramId())
                    {
                        lockTable.insert(foundIndex.getValue(), -1);
                    }
                }
                else{
                    throw new MyException("the used variable " + variable + " is not defined in the lockTable");
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
        return "Unock(" + variable + ')';
    }
}
