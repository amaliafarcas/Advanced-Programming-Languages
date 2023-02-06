package Model.Statement;
import Model.ADTStack.MyIDictionary;
import Model.ProgramState;
import Exception.MyException;
import Model.Type.StringType;
import Model.Type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException;
    //IStatement deepCopy();
}
