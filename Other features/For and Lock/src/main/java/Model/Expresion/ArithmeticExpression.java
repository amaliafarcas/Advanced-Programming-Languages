package Model.Expresion;

import Model.ADTStack.MyIDictionary;
import Exception.MyException;
import Model.ADTStack.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithmeticExpression implements Expression {
    Expression expression1;
    Expression expression2;
    int operation; //1-'+', 2-'-', 3-'*', 4-'/'

    public ArithmeticExpression(Expression expression1, Expression expression2, int operation) {
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

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable,  MyIHeap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = expression1.evaluate(symTable, heap);
        if (v1.getType().equals(new IntType())) {

            v2 = expression2.evaluate(symTable, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (operation == 1) return new IntValue(n1 + n2);
                if (operation == 2) return new IntValue(n1 - n2);
                if (operation == 3) return new IntValue(n1 * n2);
                if (operation == 4) {
                    if (n2 == 0) {
                        throw new MyException("division by zero");
                    } else {
                        return new IntValue(n1 / n2);
                    }
                }
            } else {
                throw new MyException("second operand is not an integer");
            }
        } else {
            throw new MyException("first operand is not an integer");
        }
        return new IntValue(0);
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

        return new IntType();
    }


    @Override
    public String toString() {
        String returnString = expression1.toString();
        if (operation == 1) returnString = returnString + "+";
        if (operation == 2) returnString = returnString + "-";
        if (operation == 3) returnString = returnString + "*";
        if (operation == 4) returnString = returnString + "/";
        return returnString + expression2.toString();
    }
}