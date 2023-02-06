package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIStack;
import Model.ProgramState;
import Model.Type.Type;
import Exception.MyException;

public class CompoundStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public IStatement getFirstStatement() {
        return firstStatement;
    }

    public void setFirstStatement(IStatement firstStatement) {
        this.firstStatement = firstStatement;
    }

    public IStatement getSecondStatement() {
        return secondStatement;
    }

    public void setSecondStatement(IStatement secondStatement) {
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        return secondStatement.typeCheck(firstStatement.typeCheck(typeEnvironment));
    }

    @Override
    public String toString() {
        return " " + firstStatement.toString() + "," + secondStatement.toString() + " ";
    }
}
