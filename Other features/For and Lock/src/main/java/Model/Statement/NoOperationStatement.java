package Model.Statement;
import Model.ADTStack.MyIDictionary;
import Model.ProgramState;
import Model.Type.Type;
import Exception.MyException;

public class NoOperationStatement implements IStatement {

    //TODO implement
    @Override
    public ProgramState execute(ProgramState state){

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "NoOp";
    }
}
