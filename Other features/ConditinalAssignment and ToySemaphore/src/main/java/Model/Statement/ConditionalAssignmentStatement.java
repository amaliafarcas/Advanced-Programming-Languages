package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIStack;
import Model.Expresion.Expression;
import Model.Expresion.RelationalExpression;
import Model.Expresion.VariableExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.Value;

public class ConditionalAssignmentStatement implements IStatement{

    Expression expression1;
    Expression expression2;
    Expression expression3;

    public ConditionalAssignmentStatement(Expression expression1, Expression expression2, Expression expression3) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public void setExpression1(Expression expression1) {
        this.expression1 = expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }

    public void setExpression2(Expression expression2) {
        this.expression2 = expression2;
    }

    public Expression getExpression3() {
        return expression3;
    }

    public void setExpression3(Expression expression3) {
        this.expression3 = expression3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();


        Value value1 = expression1.evaluate(symTbl, state.getHeap());
        if (! value1.getType().equals(new BoolType())){
            throw new MyException("Expression 1 type not bool");
        }
        Value value2 = expression2.evaluate(symTbl, state.getHeap());
        if (! value2.getType().equals(new IntType())){
            throw new MyException("Expression 2 type not int");
        }
        Value value3 = expression3.evaluate(symTbl, state.getHeap());

        if (! value3.getType().equals(new IntType())){
            throw new MyException("Expression 3 type not int");
        }

        IStatement condAssignmentStatement=
                new IfStatement(expression1,new AssignmentStatement("v", expression2), new AssignmentStatement("v", expression3));

        stack.push(condAssignmentStatement);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        /*Type typeExpression1 = expression1.typeCheck(typeEnvironment);
        Type typeExpression2 = expression2.typeCheck(typeEnvironment);
        Type typeExpression3 = expression3.typeCheck(typeEnvironment);

        if(!typeExpression1.equals(new BoolType())){
            throw new MyException("Expression 1 is not BoolType");
        }
        if(!typeExpression2.equals(new IntType())){
            throw new MyException("Expression 2 is not IntType");
        }
        if(!typeExpression3.equals(new IntType())){
            throw new MyException("Expression 3 is not IntType");
        }*/


        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "v=" +
                "(" + expression1 +
                ")?" + expression2 +
                ":" + expression3 +
                ' ';
    }
}
