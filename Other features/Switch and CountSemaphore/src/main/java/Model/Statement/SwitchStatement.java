package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIStack;
import Model.Expresion.Expression;
import Model.Expresion.LogicExpression;
import Model.Expresion.RelationalExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.Value;


public class SwitchStatement implements IStatement{

    Expression expression;
    Expression expression1;
    Expression expression2;
    IStatement statement1;
    IStatement statement2;

    IStatement statement3;

    public SwitchStatement(Expression expression, Expression expression1, Expression expression2, IStatement statement1, IStatement statement2, IStatement statement3) {
        this.expression = expression;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        Value value = expression.evaluate(symTbl, state.getHeap());
        Value value1 = expression1.evaluate(symTbl, state.getHeap());
        Value value2 = expression2.evaluate(symTbl, state.getHeap());
        if (! (value.getType().equals(value1.getType()) && value.getType().equals(value2.getType()))){
            throw new MyException("Expressions " + expression + ", " + expression1+ ", " + expression2 + " do NOT have the same type");
        }

        IStatement switchStatement = new IfStatement(new RelationalExpression(expression, expression1, "=="), statement1, new IfStatement(new RelationalExpression(expression, expression2, "=="), statement2, statement3));

        stack.push(switchStatement);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeExpression = expression.typeCheck(typeEnvironment);
        Type typeExpression1 = expression1.typeCheck(typeEnvironment);
        Type typeExpression2 = expression2.typeCheck(typeEnvironment);

        if(!(typeExpression.equals(typeExpression1) && typeExpression.equals(typeExpression2))){
            throw new MyException("Expressions " + expression + ", " + expression1+ ", " + expression2 + " do NOT have the same type");
        }

        statement1.typeCheck(typeEnvironment.deepCopy());
        statement2.typeCheck(typeEnvironment.deepCopy());
        statement3.typeCheck(typeEnvironment.deepCopy());

        return null;
    }

    @Override
    public String toString() {
        return "Switch(" + expression +
                "(case " + expression1 +
                " : " + statement1 +
                ")(case " +  expression2 +
                " : " + statement2 +
                ")(default : " + statement3 +
                "))";
    }
}
