package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIStack;
import Exception.MyException;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStatement implements IStatement {

    Expression operation;
    IStatement ifStatement;
    IStatement elseStatement;

    public IfStatement(Expression operation, IStatement ifStatement, IStatement elseStatement) {
        this.operation = operation;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    public Expression getOperation() {
        return operation;
    }

    public void setOperation(Expression operation) {
        this.operation = operation;
    }

    public IStatement getIfStatement() {
        return ifStatement;
    }

    public void setIfStatement(IStatement ifStatement) {
        this.ifStatement = ifStatement;
    }

    public IStatement getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(IStatement elseStatement) {
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        Value cond = operation.evaluate(symTbl, state.getHeap());
        if (cond.getType().equals(new BoolType())) {
            BoolValue v = (BoolValue) cond;
            boolean b = v.getValue();
            if (b) {
                stack.push(ifStatement);
            } else {
                stack.push(elseStatement);
            }
        } else {
            throw new MyException("Cond type not bool");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeOperation = operation.typeCheck(typeEnvironment);

        if(!typeOperation.equals(new BoolType())){
            throw new MyException("The condition of IF is not BoolType");
        }

        ifStatement.typeCheck(typeEnvironment.deepCopy());
        elseStatement.typeCheck(typeEnvironment.deepCopy());

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return " (IF(" + operation.toString()
                + ")THEN("
                + ifStatement.toString() + ")ELSE(" +
                elseStatement.toString() + "))";
    }
}
