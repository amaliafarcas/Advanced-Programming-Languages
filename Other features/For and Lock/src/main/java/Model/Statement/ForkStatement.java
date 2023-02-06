package Model.Statement;

import Model.ADTStack.*;
import Model.ProgramState;
import Exception.MyException;
import Model.Type.Type;
import Model.Value.Value;

import java.io.BufferedReader;

public class ForkStatement implements IStatement{

    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public IStatement getStatement() {
        return statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return new ProgramState(new MyStack<>(), state.getSymTable().deepCopy(), state.getOut(), state.getFileTable(), state.getHeap(), state.getLockTable(), statement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        statement.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() +
                ')';
    }
}
