package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIList;
import Exception.MyException;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;


public class PrintStatement implements IStatement {

    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIList<Value> list = state.getOut();
        list.add(expression.evaluate(state.getSymTable(), state.getHeap()));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        expression.typeCheck(typeEnvironment);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return " print(" + expression.toString() + ")";
    }
}
