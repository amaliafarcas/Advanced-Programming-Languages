package Model.Expresion;

import Model.ADTStack.MyIDictionary;
import Exception.MyException;
import Model.ADTStack.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationalExpression implements Expression{

    Expression expression1;
    Expression expression2;

    String operation;// <, <=, ==, !=, >, >=

    public RelationalExpression(Expression expression1, Expression expression2, String operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable,  MyIHeap<Integer, Value> heap) throws MyException {
        Value value1, value2;

        value1 = expression1.evaluate(symTable, heap);
        if(value1.getType().equals(new IntType())){

            value2 = expression2.evaluate(symTable, heap);
            if(value2.getType().equals(new IntType())){

                IntValue iValue1 = (IntValue) value1;
                IntValue iValue2 = (IntValue) value2;

                int first, second;
                first = iValue1.getValue();
                second = iValue2.getValue();

                if(operation.equals("<")) return new BoolValue(first<second);
                if(operation.equals("<=")) return new BoolValue(first<=second);
                if(operation.equals("==")) return new BoolValue(first==second);
                if(operation.equals("!=")) return new BoolValue(first!=second);
                if(operation.equals(">")) return new BoolValue(first>second);
                if(operation.equals(">=")) return new BoolValue(first>=second);
            }
            else throw new MyException("Expression2 not an integer");
        }
        else throw new MyException("Expression1 not an integer");

        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnvironment);
        type2 = expression2.typeCheck(typeEnvironment);

        if (!type1.equals(new IntType())){
            throw new MyException("first operand is not an integer");
        }

        if (!type2.equals(new IntType())){
            throw new MyException("second operand is not an integer");
        }

        return new BoolType();
    }

    @Override
    public String toString() {
        return expression1 + operation + expression2;
    }
}
