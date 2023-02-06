package Model.Expresion;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import Exception.MyException;


public class MULExpression implements Expression{

    Expression expression1;
    Expression expression2;

    public MULExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> heap) throws MyException {

        Value value1 = expression1.evaluate(symTable, heap);

        if (!value1.getType().equals(new IntType())) {
            throw new MyException("Expression "+expression1+" not int type");
        }
        Value value2 = expression2.evaluate(symTable, heap);

        if (!value2.getType().equals(new IntType())) {
            throw new MyException("Expression "+expression2+" not int type");
        }

        IntValue result =(IntValue) new ArithmeticExpression(new ArithmeticExpression(expression1, expression2, 3), new ArithmeticExpression(expression1, expression2, 1), 2).evaluate(symTable, heap);

        return new IntValue(result.getValue());
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
        return "MUL(" + expression1 +", "+ expression2 +
                ')';
    }
}
