package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyILatchTable;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;


public class CountDownStatement implements IStatement{

    String variable;

    public CountDownStatement(String variable) {
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

        if(latchTable.lookUp(foundIndex.getValue()) > 0){
            latchTable.update(foundIndex.getValue(), latchTable.lookUp(foundIndex.getValue())-1);
        }
        state.getOut().add(new IntValue(state.getProgramId()));


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
        return "CountDown(" + variable +
                ')';
    }
}
