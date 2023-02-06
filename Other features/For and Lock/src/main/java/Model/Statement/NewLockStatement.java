package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.ADTStack.MyILockTable;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.ReferenceValue;
import Model.Value.Value;


public class NewLockStatement implements IStatement{

    String variable;


    public NewLockStatement(String variable) {
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
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        MyILockTable<Integer, Integer> lockTable = state.getLockTable();

        if (symTbl.isDefined(variable)) {
            Type typeId = (symTbl.lookUp(variable)).getType();

            if (typeId instanceof IntType) {

                int newFreeLocation = lockTable.getNextFreeAddress();
                lockTable.insert(newFreeLocation, -1);
                symTbl.update(variable, new IntValue(newFreeLocation));

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
        return "newLock(" + variable + ')';
    }
}
