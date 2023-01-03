package Model.Statement;

import Exception.MyException;
import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIStack;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class WhileStatement implements IStatement{

    Expression expression;
    IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        Value condition = expression.evaluate(symTbl, state.getHeap());

        if (condition.getType().equals(new BoolType())) {
            BoolValue conditionValue = (BoolValue) condition;
            boolean conditionV = conditionValue.getValue();

            if (conditionV) {
                stack.push(this);
                stack.push(statement);
            }
        } else {
            throw new MyException("Cond type not bool");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeExpression = expression.typeCheck(typeEnvironment);

        if(!typeExpression.equals(new BoolType())){
            throw new MyException("The condition of WHILE is not BoolType");
        }

        statement.typeCheck(typeEnvironment.deepCopy());

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "(while (" + expression +
                ") " + statement +
                ')';
    }
}
