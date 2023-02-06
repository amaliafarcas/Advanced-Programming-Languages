package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIStack;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;


public class SleepStatement implements IStatement{

    Value number;

    public SleepStatement(Value number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        MyIStack<IStatement> stack = state.getExeStack();

        if(!(number.getType() instanceof IntType)){
            throw new MyException("Variable "+number+" not an int");
        }

        IntValue value = (IntValue) number;
        if(value.getValue()!=0){
            stack.push(new SleepStatement(new IntValue(value.getValue()-1)));
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Sleep(" + number +
                ')';
    }
}
